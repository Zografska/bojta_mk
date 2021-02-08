package com.example.bojta_mk.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Entity
@Table(name = "user_table")
public class User {

    String name;
    @Id
    String email;
    String phone;
    String password;

    public User() {
    }

    public User(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password= password;
    }
}
