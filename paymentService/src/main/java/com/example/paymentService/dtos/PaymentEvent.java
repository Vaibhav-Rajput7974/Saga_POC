package com.example.paymentService.dtos;

import lombok.Data;

@Data
public class PaymentEvent {

    private String type;

    private CustomerOrder order;

}
