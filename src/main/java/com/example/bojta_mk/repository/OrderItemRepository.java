package com.example.bojta_mk.repository;

import com.example.bojta_mk.model.OrderItem;
import com.example.bojta_mk.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Optional<OrderItem> findByProductAndDimensionAndQuantity(Product p, String dimension, int quantity);
}
