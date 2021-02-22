package com.example.bojta_mk.model.exeptions;

public class UsernameMustBeAnEmailException extends RuntimeException {
    public UsernameMustBeAnEmailException(String message) {
        super(String.format("The username: %s is not a valid e-mail address! Your username must be a valid e-mail address", message));
    }
}
