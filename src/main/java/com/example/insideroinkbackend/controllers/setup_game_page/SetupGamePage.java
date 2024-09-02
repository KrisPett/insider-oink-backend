package com.example.insideroinkbackend.controllers.setup_game_page;

import com.example.insideroinkbackend.controllers.setup_game_page.DTOs.SetupGamePageDTO;
import com.example.insideroinkbackend.models.Game;
import com.example.insideroinkbackend.models.Player;
import com.example.insideroinkbackend.services.ChatService;
import com.example.insideroinkbackend.services.GameService;
import com.example.insideroinkbackend.services.PlayerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/insider")
@Slf4j
public class SetupGamePage {
    private final GameService gameService;
    private final PlayerService playerService;
    private final ChatService chatService;

    @GetMapping("game/{gameId}/setup-game-page")
    public ResponseEntity<SetupGamePageDTO> setupGamePage(@PathVariable String gameId) {
        log.info("setupGamePage");
        DTOBuilder dtoBuilder = new DTOBuilder();
        dtoBuilder.setGame(gameService.findById(gameId));
        return ResponseEntity.ok(SetupGamePage.toDTO(dtoBuilder));
    }

    @PostMapping("game/{gameId}/join")
    public ResponseEntity<SetupGamePageDTO> joinGame(@PathVariable String gameId, @RequestParam String playerName) {
        log.info("Joining game {}", gameId);
        Player player = playerService.joinGame(gameId, playerName);
        DTOBuilder dtoBuilder = new DTOBuilder();
        dtoBuilder.setGame(gameService.findById(gameId));
        return ResponseEntity.ok(SetupGamePage.toDTO(dtoBuilder));
    }

    @PostMapping("game/{gameId}/start")
    public ResponseEntity<String> startGame(@PathVariable String gameId) {
        log.info("Starting game {}", gameId);
        Game game = gameService.startGame(gameId);
        chatService.save(game);
        return ResponseEntity.ok("Game Started");
    }

    @PostMapping("game/{gameId}/create-player")
    public ResponseEntity<SetupGamePageDTO> createPLayer(@PathVariable String gameId, @RequestParam String playerName) {
        log.info("createPLayer");
        playerService.save(playerName);
        DTOBuilder dtoBuilder = new DTOBuilder();
        dtoBuilder.setGame(gameService.findById(gameId));
        return ResponseEntity.ok(SetupGamePage.toDTO(dtoBuilder));
    }

    private static SetupGamePageDTO toDTO(DTOBuilder dtoBuilder) {
        return new SetupGamePageDTO(
                dtoBuilder.getGame().getId(),
                dtoBuilder.getGame().getPlayers().stream()
                        .map(SetupGamePage::toDTO)
                        .toArray(SetupGamePageDTO.Player[]::new));
    }

    private static SetupGamePageDTO.Player toDTO(Player player) {
        return new SetupGamePageDTO.Player(
                player.getId(),
                player.getName());
    }

    @Data
    private static class DTOBuilder {
        private Game game;
    }
}
