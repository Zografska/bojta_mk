package com.example.bojta_mk.model.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class OrderItemAlreadyInShoppingCart extends RuntimeException {
    public OrderItemAlreadyInShoppingCart() {
        super("That product is already in your shopping cart!");
    }
}
