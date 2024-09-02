package com.example.insideroinkbackend.exceptions;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(String id) {
        super(String.format("not found. Id: %s", id));
    }
}
