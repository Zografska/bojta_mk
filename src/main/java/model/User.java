package model;

import lombok.Data;


@Data
public class User {
    String name;
    String email;
    String phone;

    public User() {
    }

    public User(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
