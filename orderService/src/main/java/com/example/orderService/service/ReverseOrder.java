package com.example.orderService.service;

import com.example.orderService.dto.OrderEvent;
import com.example.orderService.entity.Order;
import com.example.orderService.entity.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReverseOrder {

    @Autowired
    private OrderRepository orderRepository;


    @KafkaListener(topics = "reversed-orders", groupId = "orders-group")
    public void reverseOrder(String event) throws JsonProcessingException {
        System.out.println("Inside Reverse order");
        try{
            OrderEvent orderEvent = new ObjectMapper().readValue(event,OrderEvent.class);
            Optional<Order> optionalOrder = orderRepository.findById(orderEvent.getOrder().getOrderId());
            System.out.println(optionalOrder.get());
            if (optionalOrder.isPresent()){
                orderRepository.delete(optionalOrder.get());
            }
        }catch (Exception e){
            System.out.println("Error occur while reverting the order");
        }
    }
}
