package com.example.insideroinkbackend.controllers.game_in_progress_page;

import com.example.insideroinkbackend.models.Chat;
import com.example.insideroinkbackend.models.Game;
import com.example.insideroinkbackend.models.Guess;
import com.example.insideroinkbackend.models.Player;
import com.example.insideroinkbackend.services.ChatService;
import com.example.insideroinkbackend.services.GameService;
import com.example.insideroinkbackend.services.GuessService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/insider")
@Slf4j
public class GameInProgressPage {
    private final GameService gameService;
    private final GuessService guessService;
    private final ChatService chatService;

    @GetMapping("game/{gameId}/game-in-progress-page")
    public ResponseEntity<GameInProgressPageDTO> gameInProgressPage(@PathVariable String gameId) {
        log.info("gameInProgressPage");
        Game game = gameService.findById(gameId);
        Chat chat = chatService.findByGame(game);

        DTOBuilder dtoBuilder = new DTOBuilder();
        dtoBuilder.setGame(game);
        dtoBuilder.setChat(chat);

        return ResponseEntity.ok(GameInProgressPage.toDTO(dtoBuilder));
    }

    @PostMapping("game/{gameId}/guess")
    public ResponseEntity<GameInProgressPageDTO> guessWord(@PathVariable String gameId, @RequestParam String guessWord) {
        log.info("guessWord");
        guessService.guessWord(gameService.findById(gameId), guessWord, "", "");

        Game game = gameService.findById(gameId);
        Chat chat = chatService.findByGame(game);
        DTOBuilder dtoBuilder = new DTOBuilder();
        dtoBuilder.setGame(game);
        dtoBuilder.setChat(chat);

        return ResponseEntity.ok(GameInProgressPage.toDTO(dtoBuilder));
    }

    private static GameInProgressPageDTO toDTO(DTOBuilder dtoBuilder) {
        return new GameInProgressPageDTO(
                "",
                toDTO(dtoBuilder.getGame()),
                toDTO(dtoBuilder.getChat())
        );
    }

    private static GameInProgressPageDTO.Game toDTO(Game game) {
        return new GameInProgressPageDTO.Game(
                game.getId(),
                game.getStatus().toString(),
                game.getWordToGuess(),
                game.getPlayers().stream()
                        .map(GameInProgressPage::toDTO)
                        .toArray(GameInProgressPageDTO.Player[]::new));
    }

    private static GameInProgressPageDTO.Player toDTO(Player player) {
        return new GameInProgressPageDTO.Player(
                player.getId(),
                player.getName(),
                player.getRole().toString(),
                player.isInsider());
    }

    private static GameInProgressPageDTO.Chat toDTO(Chat chat) {
        return new GameInProgressPageDTO.Chat(
                chat.getId(),
                chat.getGuesses().stream()
                        .map(GameInProgressPage::toDTO)
                        .toArray(GameInProgressPageDTO.Guess[]::new));
    }

    private static GameInProgressPageDTO.Guess toDTO(Guess guess) {
        return new GameInProgressPageDTO.Guess(
                guess.getId(),
                guess.getMessage(),
                guess.getPlayerId(),
                guess.getRole());
    }

    @Data
    private static class DTOBuilder {
        private Game game;
        private Chat chat;

        public DTOBuilder() {

        }
    }
}
