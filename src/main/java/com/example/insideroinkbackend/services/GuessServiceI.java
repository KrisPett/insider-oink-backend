package com.example.insideroinkbackend.services;

import com.example.insideroinkbackend.models.Chat;
import com.example.insideroinkbackend.models.Game;
import com.example.insideroinkbackend.models.Guess;

public interface GuessServiceI {
    void save(String message, String playerId, String role, Chat chat);

    Guess findById(String id);

    void guessWord(Game game, String message, String playerId, String role);
}
