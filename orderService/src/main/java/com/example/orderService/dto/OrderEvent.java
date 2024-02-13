package com.example.orderService.dto;

import lombok.Data;

@Data
public class OrderEvent {

    private String type;

    private CustomerOrder order;

}