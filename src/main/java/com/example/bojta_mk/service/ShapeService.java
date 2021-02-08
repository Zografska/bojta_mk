package com.example.bojta_mk.service;


import com.example.bojta_mk.model.Shape;

import java.util.List;

public interface ShapeService {
    Shape create(String name, List<String> dimensions);
}
