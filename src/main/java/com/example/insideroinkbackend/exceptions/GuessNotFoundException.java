package com.example.insideroinkbackend.exceptions;

public class GuessNotFoundException extends RuntimeException {
    public GuessNotFoundException(String id) {
        super(String.format("not found. Id: %s", id));
    }
}
