package com.example.insideroinkbackend.exceptions;

public class ChatNotFoundException extends RuntimeException {
    public ChatNotFoundException(String id) {
        super(String.format("not found. Id: %s", id));
    }
}
