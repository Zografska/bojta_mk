package service;

import model.Shape;

import java.util.List;

public interface ShapeService {
    Shape create(String name, List<String> dimensions);
}
