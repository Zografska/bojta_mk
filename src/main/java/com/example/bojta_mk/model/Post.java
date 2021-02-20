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
    String image;

    public Post(String headline, String description, String image) {
        this.headline = headline;
        this.description = description;
        this.image=image;
    }

    public Post() {
    }
}
