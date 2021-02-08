package model;

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
    String description;

    public Product() {
    }
    public Product(String id, Category category, Shape shape, String name, String description) {
        this.id = id;
        this.category = category;
        this.shape = shape;
        this.name = name;
        this.description = description;
    }
}
