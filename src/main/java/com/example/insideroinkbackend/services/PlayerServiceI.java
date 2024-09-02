package com.example.insideroinkbackend.services;

import com.example.insideroinkbackend.models.Player;

import java.util.List;

public interface PlayerServiceI {
    Player save(String playerName);
    Player joinGame(String gameId, String playerName);

    Player findById(String playerId);

    List<Player> findall();

    String voteForInsider(String playerId);
}
