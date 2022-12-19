package com.example.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


import com.example.boot.entities.User;

import java.util.List;
import java.util.ArrayList;

@RestController // this tells Spring we are using this class as a controller to handle http responses
public class GreetingController {

    private static List<User> users;
    private int count = 1;

    public GreetingController(){
        users = new ArrayList<>();
    }

    @GetMapping("/hello") // maps GET requests to /hello with the method below
    public String helloWorld(){
        return "Hello world!"; // Spring handles attaching this String to the http response body
    }

    @GetMapping("/hello/{name}")
    public String personalGreeting(@PathVariable String name){ // @PathVariable tells Spring to pass it in as a String into this method
        return "Hello " + name;
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user){ // @ Request body tells Spring to get the info for this user from the request body
        user.setId(count);
        users.add(user);
        count++;
        return user; // Spring will handle turning the Java object back into a JSON
        // see User class for example of how to set up entity
    }

    @GetMapping("/user/{index}")
    public ResponseEntity<User> getUser(@PathVariable int index){
        // we can use the ResponseEntity class to set the response body and http status
        return new ResponseEntity<>(users.get(index), HttpStatus.I_AM_A_TEAPOT);
    }
    
}
