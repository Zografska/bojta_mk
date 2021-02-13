package com.example.bojta_mk.model;

import com.example.bojta_mk.model.enumerations.ShoppingCartStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToMany
    List<OrderItem> productList;
    @ManyToOne
    User user;
    @Enumerated(EnumType.STRING)
    ShoppingCartStatus status;

    public ShoppingCart() {
    }

    public ShoppingCart(User user) {
        this.productList = new ArrayList<>();
        this.user = user;
        this.status = ShoppingCartStatus.ACTIVE;
    }
}
