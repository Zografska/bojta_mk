package com.example.bojta_mk.model.exeptions;

public class ShapeNotFoundException extends RuntimeException {
    public ShapeNotFoundException(String message) {
        super(String.format("Shape with id: %s does not exist", message));
    }
}
