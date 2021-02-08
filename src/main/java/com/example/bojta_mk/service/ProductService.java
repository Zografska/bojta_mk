package com.example.bojta_mk.service;


import com.example.bojta_mk.model.Category;
import com.example.bojta_mk.model.Product;
import com.example.bojta_mk.model.Shape;

import javax.persistence.OneToOne;

public interface ProductService {
    Product create(String id, Category category, Shape shape, String name, String description);
}
