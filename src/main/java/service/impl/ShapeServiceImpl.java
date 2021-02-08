package service.impl;

import model.Shape;
import repository.ShapeRepository;
import service.ShapeService;

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
