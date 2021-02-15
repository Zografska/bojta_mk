package com.example.bojta_mk.service;

import com.example.bojta_mk.model.OrderItem;
import com.example.bojta_mk.model.Product;

import java.util.List;
import java.util.Optional;

public interface OrderItemService {
    List<OrderItem> findAll();
    OrderItem findById(Long id);
    OrderItem create(Product p, String dimension);
    Optional<OrderItem> findByProductAndDimensionAndQuantity(Product p, String dimension, int quantity);
    void deleteById(Long id);
    OrderItem editQuantity(Long id, int quantity);
}
