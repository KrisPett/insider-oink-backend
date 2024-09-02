package com.example.insideroinkbackend.services;

import com.example.insideroinkbackend.models.Chat;
import com.example.insideroinkbackend.models.Game;

public interface ChatServiceI {
    void save(Game game);

    Chat findById(String id);

    Chat findByGame(Game game);
}
