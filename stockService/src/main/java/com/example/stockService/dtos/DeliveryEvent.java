package com.example.stockService.dtos;

import lombok.Data;

@Data
public class DeliveryEvent {

    private String type;

    private CustomerOrder order;

}