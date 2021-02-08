package model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Data
@Entity
public class Product {
    @Id
    String id;
    Category category;
    @OneToOne
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
