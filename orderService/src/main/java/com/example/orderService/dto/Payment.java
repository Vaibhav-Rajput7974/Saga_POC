package com.example.orderService.dto;

import lombok.Data;

@Data
public class Payment {

    private String mode;

    private Long orderId;

    private double amount;

}