package com.example.insideroinkbackend.services;

import com.example.insideroinkbackend.exceptions.GameNotFoundException;
import com.example.insideroinkbackend.exceptions.PlayerNotFoundException;
import com.example.insideroinkbackend.models.Player;
import com.example.insideroinkbackend.repositories.GameRepository;
import com.example.insideroinkbackend.repositories.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService implements PlayerServiceI {
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;

    @Override
    public Player save(String playerName) {
        Player player = Player.create(playerName, null);
        return playerRepository.save(player);
    }

    @Override
    public Player joinGame(String gameId, String playerName) {
        var game = gameRepository.findById(gameId)
                .orElseThrow(() -> new GameNotFoundException(gameId));
        var player = Player.create(playerName, game);
        return playerRepository.save(player);
    }

    @Override
    public Player findById(String playerId) {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException(playerId));
    }

    @Override
    public List<Player> findall() {
        return playerRepository.findAll();
    }

    @Override
    public String voteForInsider(String playerId) {
        Player playerVotedFor = findById(playerId);
        if (playerVotedFor.isInsider())
            return "Correct! Player " + playerVotedFor.getName() + " was the Insider.";
        else
            return "Incorrect. Player " + playerVotedFor.getName() + " was not the Insider.";
    }

}
