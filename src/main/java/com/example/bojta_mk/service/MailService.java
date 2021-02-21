package com.example.bojta_mk.service;

public interface MailService {
    void sendOrderMail(String name, Long id);
    void sendQuestion(String message);
    void sendRegisterConfirmation(String username,String name,String surname);
}
