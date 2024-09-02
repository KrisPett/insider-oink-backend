package com.example.insideroinkbackend.exceptions;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(String id) {
        super(String.format("not found. Id: %s", id));
    }
}
