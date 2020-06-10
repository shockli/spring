package com.example.restservice;

import com.example.restservice.service.FullNameComposer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class RestServiceApplication implements CommandLineRunner{
    @Autowired
    GreetingController greetingController;


    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        greetingController.greeting("Petrov");
        greetingController.composeFullName("я", "Petrov");
    }
}
