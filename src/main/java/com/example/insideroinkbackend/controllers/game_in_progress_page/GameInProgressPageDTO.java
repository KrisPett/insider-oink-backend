package com.example.insideroinkbackend.controllers.game_in_progress_page;

public record GameInProgressPageDTO(
        String id,
        Game game,
        Chat chat
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

    public record Chat(
            String id,
            Guess[] guesses
    ) {
    }

    public record Guess(
            String id,
            String message,
            String playerId,
            String role
    ) {
    }
}
