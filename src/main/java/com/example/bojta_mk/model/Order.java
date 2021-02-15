package com.example.bojta_mk.model;

import com.example.bojta_mk.model.enumerations.OrderStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "ordersTable")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToOne
    ShoppingCart shoppingCart;

    @Enumerated
    OrderStatus orderStatus;

    public Order() {
    }

    public Order(ShoppingCart shoppingCart, OrderStatus orderStatus) {
        this.shoppingCart = shoppingCart;
        this.orderStatus = orderStatus;
    }
}
