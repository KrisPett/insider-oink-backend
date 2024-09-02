package com.example.insideroinkbackend.services;

import com.example.insideroinkbackend.models.Game;

import java.util.List;

public interface GameServiceI {
    Game save(String wordToGuess, Game.Status status);

    List<Game> findAll();

    Game findById(String id);

    Game startGame(String gameId);
}
