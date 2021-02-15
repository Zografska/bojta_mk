package com.example.bojta_mk.service.impl;

import com.example.bojta_mk.model.Order;
import com.example.bojta_mk.model.OrderItem;
import com.example.bojta_mk.model.ShoppingCart;
import com.example.bojta_mk.model.User;
import com.example.bojta_mk.model.enumerations.OrderStatus;
import com.example.bojta_mk.repository.OrderRepository;
import com.example.bojta_mk.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order findByShoppingCart(ShoppingCart shoppingCart) {
        return this.orderRepository.findByShoppingCart(shoppingCart);
    }

    @Override
    public Order create(ShoppingCart shoppingCart, OrderStatus orderStatus) {
        Order order = new Order(shoppingCart, orderStatus);
        return this.orderRepository.save(order);
    }
}
