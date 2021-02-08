package com.example.bojta_mk.repository;

import com.example.bojta_mk.model.Shape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShapeRepository extends JpaRepository<Shape, String> {

}
