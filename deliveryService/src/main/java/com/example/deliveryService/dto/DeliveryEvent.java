package com.example.deliveryService.dto;

import lombok.Data;

@Data
public class DeliveryEvent {

    private String type;

    private CustomerOrder order;

}
