package service.impl;

import model.Category;
import model.Product;
import model.Shape;
import org.springframework.stereotype.Service;
import repository.ProductRepository;
import service.ProductService;

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
