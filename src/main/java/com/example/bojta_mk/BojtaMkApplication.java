package com.example.bojta_mk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(scanBasePackages = {"web.controller"})
public class BojtaMkApplication {

    public static void main(String[] args) {
        SpringApplication.run(BojtaMkApplication.class, args);
    }

}
