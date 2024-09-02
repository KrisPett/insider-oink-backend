package com.example.insideroinkbackend.services;

import com.example.insideroinkbackend.exceptions.GameNotFoundException;
import com.example.insideroinkbackend.models.Game;
import com.example.insideroinkbackend.models.Player;
import com.example.insideroinkbackend.repositories.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService implements GameServiceI {
    private final GameRepository gameRepository;

    @Override
    public Game save(String wordToGuess, Game.Status status) {
        return gameRepository.save(Game.create(wordToGuess, status));
    }

    @Override
    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public Game findById(String id) {
        return gameRepository.findById(id).orElseThrow(() -> new GameNotFoundException(id));
    }

    @Override
    public Game startGame(String gameId) {
        Game game = findById(gameId);

        List<Player> players = game.getPlayers();

        if (players.size() < 4)
            throw new IllegalStateException("At least four players are required to start the game.");

        Collections.shuffle(players);
        assignRoles(players);

        return gameRepository.save(game);
    }

    private void assignRoles(List<Player> players) {
        players.get(0).setRole(Player.Role.MASTER);
        players.get(1).setRole(Player.Role.INSIDER);

        players.stream()
                .skip(2)
                .forEach(player -> player.setRole(Player.Role.COMMONER));

        players.forEach(player -> player.setInsider(player.getRole() == Player.Role.INSIDER));
    }

}
