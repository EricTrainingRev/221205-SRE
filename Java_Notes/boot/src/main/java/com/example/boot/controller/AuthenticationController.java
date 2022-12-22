package com.example.boot.controller;


import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    
    @PostMapping("/login")
    public String login(HttpSession session){
        session.setAttribute("user", "some username");
        return "logged in successfully";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "logged out";
    }

}
