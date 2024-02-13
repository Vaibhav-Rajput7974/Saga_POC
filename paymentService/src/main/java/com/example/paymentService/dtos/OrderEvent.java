package com.example.paymentService.dtos;

import lombok.Data;

@Data
public class OrderEvent {

    private String type;

    private CustomerOrder order;

}
