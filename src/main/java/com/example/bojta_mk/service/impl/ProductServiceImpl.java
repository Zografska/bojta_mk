package com.example.bojta_mk.service.impl;

import com.example.bojta_mk.model.enumerations.Category;
import com.example.bojta_mk.model.Product;
import com.example.bojta_mk.model.Shape;
import com.example.bojta_mk.model.exeptions.ProductNotFoundException;
import com.example.bojta_mk.service.ProductService;
import org.springframework.stereotype.Service;
import com.example.bojta_mk.repository.ProductRepository;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(String id, Category category, Shape shape, String name) {
        return productRepository.save(new Product(id,category,shape,name));
    }

    @Override
    public Product findById(String id) {
        return this.productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return this.productRepository.findByCategory(category);
    }

    @Override
    public void deleteById(String id) { this.productRepository.deleteById(id); }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public List<Product> findByNameOrId(String str) {
        str="%"+str+"%";
        return productRepository.findByNameLikeOrIdLike(str,str);
    }
}
