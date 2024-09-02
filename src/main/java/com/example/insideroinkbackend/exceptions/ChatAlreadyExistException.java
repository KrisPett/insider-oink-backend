package com.example.insideroinkbackend.exceptions;

public class ChatAlreadyExistException extends RuntimeException {
    public ChatAlreadyExistException(String id) {
        super(String.format("not found. Id: %s", id));
    }
}
