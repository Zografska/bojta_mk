package com.example.bojta_mk.model.exeptions;


public class InvalidUserCredentialsException extends RuntimeException {

    public InvalidUserCredentialsException() {
        super("Погрешен e-mail или лозинка");
    }
}

