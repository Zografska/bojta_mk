package com.example.bojta_mk.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    Product product;

    String dimension;
    int quantity;

    public OrderItem(Product product, String dimension, int quantity) {
        this.product = product;
        this.dimension = dimension;
        this.quantity= quantity;
    }

    public OrderItem() {
    }

    public List<String> getParsedData(){
        List<String> parsed = new ArrayList<>();
        parsed.add(product.id);
        parsed.add(product.name);
        parsed.add(product.shape.name);
        parsed.add(product.category.toString());
        parsed.add(dimension);
        parsed.add(String.valueOf(quantity));
        return parsed;
    }
}
