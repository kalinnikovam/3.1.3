package com.example.firstspringbootsecurityapp;

import com.example.firstspringbootsecurityapp.models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstSpringBootSecurityAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstSpringBootSecurityAppApplication.class, args);
        User user = new User();
        System.out.println("_______________________________");
        System.out.println(user);
    }

}
