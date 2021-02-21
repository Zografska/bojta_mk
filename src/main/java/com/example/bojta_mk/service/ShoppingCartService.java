package com.example.bojta_mk.service;

import com.example.bojta_mk.model.OrderItem;
import com.example.bojta_mk.model.Product;
import com.example.bojta_mk.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    List<OrderItem> listAllProductsInShoppingCart(Long id);
    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart addProductToShoppingCart(String username, Long orderItemId);
    ShoppingCart removeProductFromShoppingCart(String username, Long orderItemId);
    ShoppingCart deactivateConfirmedShoppingCart(Long id);
    List<ShoppingCart> findAll();
}
