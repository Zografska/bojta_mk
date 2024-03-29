package com.example.bojta_mk.service;


import com.example.bojta_mk.model.enumerations.Category;
import com.example.bojta_mk.model.Product;
import com.example.bojta_mk.model.Shape;

import java.util.List;

public interface ProductService {
    Product create(String id, Category category, Shape shape, String name);
    Product findById(String id);
    List<Product> findByCategory(Category category);
    void deleteById(String id);
    List<Product> findAll();
    List<Product> findByNameOrId(String str);
}
