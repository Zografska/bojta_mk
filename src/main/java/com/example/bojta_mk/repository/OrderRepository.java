package com.example.bojta_mk.repository;

import com.example.bojta_mk.model.Order;
import com.example.bojta_mk.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByShoppingCart(ShoppingCart shoppingCart);
}
