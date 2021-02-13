package com.example.bojta_mk.model.exeptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(String.format("Product with id: %s not found", message));
    }
}
