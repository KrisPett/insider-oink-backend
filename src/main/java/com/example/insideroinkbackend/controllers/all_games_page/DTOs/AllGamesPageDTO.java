package com.example.insideroinkbackend.controllers.all_games_page.DTOs;

public record AllGamesPageDTO(
        Game[] games
) {
    public record Game(
            String id,
            String status,
            String wordToGuess,
            Player[] players) {
    }

    public record Player(
            String id,
            String name,
            String role,
            boolean isInsider) {
    }
}
