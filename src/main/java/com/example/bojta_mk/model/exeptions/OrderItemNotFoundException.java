package com.example.bojta_mk.model.exeptions;

public class OrderItemNotFoundException extends RuntimeException {
    public OrderItemNotFoundException(Long id) {
        super(String.format("Order item with id: %d not found!", id));
    }
}
