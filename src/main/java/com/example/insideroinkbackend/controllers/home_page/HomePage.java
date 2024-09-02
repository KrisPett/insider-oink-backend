package com.example.insideroinkbackend.controllers.home_page;

import com.example.insideroinkbackend.models.Game;
import com.example.insideroinkbackend.services.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/insider")
@Slf4j
public class HomePage {
    private final GameService gameService;

    @PostMapping("game/create")
    public ResponseEntity<String> createGame(@RequestParam String wordToGuess) {
        log.info("Creating game {}", wordToGuess);
        Game game = gameService.save(wordToGuess, Game.Status.NOT_STARTED);
        return ResponseEntity.ok(game.getId());
    }
}
