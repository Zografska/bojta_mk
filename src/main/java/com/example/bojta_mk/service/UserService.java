package com.example.bojta_mk.service;

import com.example.bojta_mk.model.User;
import com.example.bojta_mk.model.enumerations.Role;

public interface UserService {
    User login(String name, String password);
    User register(String username, String password, String repeatPassword, String name, String surname, String phone, Role role);
}
