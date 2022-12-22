package com.example.boot.exceptions;

public class AuthenticationFailed extends RuntimeException {
    public AuthenticationFailed(String message){
        super(message);
    }
    
}
