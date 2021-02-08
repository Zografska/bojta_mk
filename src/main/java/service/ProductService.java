package service;

import model.Category;
import model.Product;
import model.Shape;

import javax.persistence.OneToOne;

public interface ProductService {
    Product create(String id, Category category, Shape shape, String name, String description);
}
