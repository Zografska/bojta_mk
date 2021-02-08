package com.example.bojta_mk.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String headline;
    String description;

    public Post(String headline, String description) {
        this.headline = headline;
        this.description = description;
    }

    public Post() {
    }
}
