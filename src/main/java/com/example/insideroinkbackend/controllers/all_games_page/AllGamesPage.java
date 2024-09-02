package com.example.insideroinkbackend.controllers.all_games_page;

import com.example.insideroinkbackend.controllers.all_games_page.DTOs.AllGamesPageDTO;
import com.example.insideroinkbackend.models.Game;
import com.example.insideroinkbackend.models.Player;
import com.example.insideroinkbackend.services.GameService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/insider")
@Slf4j
public class AllGamesPage {
    private final GameService gameService;

    @GetMapping("game") // TODO all-game-page
    public ResponseEntity<AllGamesPageDTO> allGamesPage() {
        log.info("allGamesPage");
        DTOBuilder dtoBuilder = new DTOBuilder();
        dtoBuilder.setGames(gameService.findAll());
        return ResponseEntity.ok(AllGamesPage.toDTO(dtoBuilder));
    }

    private static AllGamesPageDTO toDTO(DTOBuilder dtoBuilder) {
        return new AllGamesPageDTO(dtoBuilder.getGames().stream()
                .map(AllGamesPage::toDTO)
                .toArray(AllGamesPageDTO.Game[]::new));
    }

    private static AllGamesPageDTO.Game toDTO(Game game) {
        return new AllGamesPageDTO.Game(
                game.getId(),
                game.getStatus().toString(),
                game.getWordToGuess(),
                game.getPlayers().stream()
                        .map(AllGamesPage::toDTO)
                        .toArray(AllGamesPageDTO.Player[]::new));
    }

    private static AllGamesPageDTO.Player toDTO(Player player) {
        return new AllGamesPageDTO.Player(
                player.getId(),
                player.getName(),
                player.getRole().toString(),
                player.isInsider());
    }

    @Data
    private static class DTOBuilder {
        private List<Game> games = new ArrayList<>();
    }
}
