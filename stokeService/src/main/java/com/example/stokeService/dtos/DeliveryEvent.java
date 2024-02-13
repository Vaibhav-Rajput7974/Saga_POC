package com.example.stokeService.dtos;

import lombok.Data;

@Data
public class DeliveryEvent {

    private String type;

    private CustomerOrder order;

}