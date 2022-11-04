package com.example.firstspringbootsecurityapp.exception_handling;

public class BadDataException extends RuntimeException {
    public BadDataException(String message) {
        super(message);
    }
}
