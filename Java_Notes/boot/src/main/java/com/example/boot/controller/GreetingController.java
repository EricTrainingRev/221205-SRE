package com.example.boot.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



@RestController // this tells Spring we are using this class as a controller to handle http responses
public class GreetingController {


    @GetMapping("/hello") // maps GET requests to /hello with the method below
    public String helloWorld(){
        return "Hello world!"; // Spring handles attaching this String to the http response body
    }

    @GetMapping("/hello/{name}")
    public String personalGreeting(@PathVariable String name){ // @PathVariable tells Spring to pass it in as a String into this method
        return "Hello " + name;
    }
    
}
