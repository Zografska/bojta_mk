package com.example.bojta_mk.repository;

import com.example.bojta_mk.model.enumerations.Category;
import com.example.bojta_mk.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {
    List<Product> findByCategory(Category category);
}
