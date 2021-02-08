package com.example.bojta_mk.model;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Data
@Entity
public class Shape {
    @Id
    String name;
    @ElementCollection
    List<String> dimensions;

    public Shape() {
    }

    public Shape(String name, List<String> dimensions) {
        this.name = name;
        this.dimensions = dimensions;
    }
}
