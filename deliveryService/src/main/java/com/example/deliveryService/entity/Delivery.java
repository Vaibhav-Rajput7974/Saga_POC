package com.example.deliveryService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Delivery {

    @Id
    @GeneratedValue
    private Long id;

    private String address;

    private String status;

    private long orderId;

}
