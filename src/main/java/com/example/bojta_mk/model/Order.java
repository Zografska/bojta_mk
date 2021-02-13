package com.example.bojta_mk.model;

import com.example.bojta_mk.model.enumerations.Status;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToMany
    List<OrderItem> productList;
    @ManyToOne
    User user;
    @Enumerated
    Status status;

    public Order() {
    }

    public Order(List<OrderItem> productList, User user, Status status) {
        this.productList = productList;
        this.user = user;
        this.status = status;
    }
}
