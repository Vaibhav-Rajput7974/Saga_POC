package com.example.orderService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "order_data")
public class Order {

    @Id
    @GeneratedValue
    private long id;

    private String item;

    private int quantity;

    private double amount;

    private String status;
}