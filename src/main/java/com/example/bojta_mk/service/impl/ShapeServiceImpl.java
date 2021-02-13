package com.example.bojta_mk.service.impl;

import com.example.bojta_mk.model.Shape;
import com.example.bojta_mk.model.exeptions.ShapeNotFoundException;
import com.example.bojta_mk.repository.ShapeRepository;
import com.example.bojta_mk.service.ShapeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShapeServiceImpl implements ShapeService {
    private final ShapeRepository shapeRepository;

    public ShapeServiceImpl(ShapeRepository shapeRepository) {
        this.shapeRepository = shapeRepository;
    }

    @Override
    public Shape create(String name, List<String> dimensions) {
        return shapeRepository.save(new Shape(name, dimensions));
    }

    @Override
    public List<Shape> findAll() {
        return shapeRepository.findAll();
    }

    @Override
    public Shape findById(String id) {
        return this.shapeRepository.findById(id).orElseThrow(() -> new ShapeNotFoundException(id));
    }
}
