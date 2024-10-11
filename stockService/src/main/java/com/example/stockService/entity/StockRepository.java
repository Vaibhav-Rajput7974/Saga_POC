package com.example.stockService.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<WareHouse, Long> {

    Iterable<WareHouse> findByItem(String item);
}