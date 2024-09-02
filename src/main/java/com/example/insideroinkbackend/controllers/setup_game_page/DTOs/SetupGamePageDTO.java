package com.example.insideroinkbackend.controllers.setup_game_page.DTOs;

public record SetupGamePageDTO(
        String id,
        Player[] players
) {
    public record Player(
            String id,
            String name
    ) {
    }
}
