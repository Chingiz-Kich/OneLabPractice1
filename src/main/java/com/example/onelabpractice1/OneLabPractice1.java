package com.example.onelabpractice1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OneLabPractice1 {

    public static void main(String[] args) {
        SpringApplication.run(OneLabPractice1.class, args);
        // Application.start();

        // Throws NullPointerException to test @ExceptionChecker
        // ExceptionAdviceCheckApp.start();
    }
}
