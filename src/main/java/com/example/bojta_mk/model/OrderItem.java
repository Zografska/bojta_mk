package com.example.bojta_mk.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    Product product;

    String dimension;

    public OrderItem(Product product, String dimension) {
        this.product = product;
        this.dimension = dimension;
    }

    public OrderItem() {
    }
}
