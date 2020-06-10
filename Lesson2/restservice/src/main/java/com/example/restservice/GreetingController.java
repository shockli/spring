package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import com.example.restservice.service.FullNameComposer;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    @LogExecutionTime
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        System.out.println("in greeting");
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @LogExecutionTime
    public String composeFullName(String firstName, String lastName) {
        return firstName + lastName;
    }
}