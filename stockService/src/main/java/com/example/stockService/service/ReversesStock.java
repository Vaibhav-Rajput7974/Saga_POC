package com.example.stockService.service;

import com.example.stockService.dtos.DeliveryEvent;
import com.example.stockService.dtos.PaymentEvent;
import com.example.stockService.entity.StockRepository;
import com.example.stockService.entity.WareHouse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReversesStock {

    @Autowired
    private StockRepository repository;

    @Autowired
    private KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    @KafkaListener(topics = "reversed-stock", groupId = "stock-group")
    public void reverseStock(String event) {
        System.out.println("Inside reverse stock for order "+event);

        try {
            DeliveryEvent deliveryEvent = new ObjectMapper().readValue(event, DeliveryEvent.class);

            Iterable<WareHouse> inv = this.repository.findByItem(deliveryEvent.getOrder().getItem());

            inv.forEach(i -> {
                i.setQuantity(i.getQuantity() + deliveryEvent.getOrder().getQuantity());
                repository.save(i);
            });

            PaymentEvent paymentEvent = new PaymentEvent();
            paymentEvent.setOrder(deliveryEvent.getOrder());
            paymentEvent.setType("PAYMENT_REVERSED");
            kafkaTemplate.send("reversed-payments", paymentEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
