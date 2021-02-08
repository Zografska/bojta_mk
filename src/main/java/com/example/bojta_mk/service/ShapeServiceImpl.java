package com.example.bojta_mk.service;

import com.example.bojta_mk.model.Shape;
import com.example.bojta_mk.repository.ShapeRepository;

import java.util.List;

public class ShapeServiceImpl implements ShapeService {
    private final ShapeRepository shapeRepository;

    public ShapeServiceImpl(ShapeRepository shapeRepository) {
        this.shapeRepository = shapeRepository;
    }

    @Override
    public Shape create(String name, List<String> dimensions) {
        return shapeRepository.save(new Shape(name, dimensions));
    }
}
