package com.example.bojta_mk.repository;

import com.example.bojta_mk.model.ShoppingCart;
import com.example.bojta_mk.model.User;
import com.example.bojta_mk.model.enumerations.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);
}
