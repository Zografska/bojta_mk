package com.example.bojta_mk.service;

import com.example.bojta_mk.model.Order;
import com.example.bojta_mk.model.OrderItem;
import com.example.bojta_mk.model.ShoppingCart;
import com.example.bojta_mk.model.User;
import com.example.bojta_mk.model.enumerations.OrderStatus;

import java.util.List;

public interface OrderService {
    Order findByShoppingCart(ShoppingCart shoppingCart);
    Order create(ShoppingCart shoppingCart, OrderStatus orderStatus);
    List<Order> findAll();
    Order changeStatus(Long id, OrderStatus status);
    Order findById(Long id);
    void delete(Long id);
}
