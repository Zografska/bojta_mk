package com.example.bojta_mk.service;

import com.example.bojta_mk.model.Category;
import com.example.bojta_mk.model.Product;
import com.example.bojta_mk.model.Shape;
import org.springframework.stereotype.Service;
import com.example.bojta_mk.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(String id, Category category, Shape shape, String name, String description) {
        return productRepository.save(new Product(id,category,shape,name,description));
    }
}
