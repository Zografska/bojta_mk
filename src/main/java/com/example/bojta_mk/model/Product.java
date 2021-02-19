package com.example.bojta_mk.model;

import com.example.bojta_mk.model.enumerations.Category;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class Product {
    @Id
    String id;
    @Enumerated(EnumType.STRING)
    Category category;
    @ManyToOne
    Shape shape;
    String name;


    public Product() {
    }
    public Product(String id, Category category, Shape shape, String name) {
        this.id = id;
        this.category = category;
        this.shape = shape;
        this.name = name;
    }
}
