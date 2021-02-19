package com.example.bojta_mk.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String headline;
    @Column(columnDefinition = "text")
    @Type(type = "text")
    String description;

    public Post(String headline, String description) {
        this.headline = headline;
        this.description = description;
    }

    public Post() {
    }
}
