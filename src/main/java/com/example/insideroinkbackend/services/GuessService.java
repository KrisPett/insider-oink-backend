package com.example.insideroinkbackend.services;

import com.example.insideroinkbackend.exceptions.GameNotFoundException;
import com.example.insideroinkbackend.exceptions.GuessNotFoundException;
import com.example.insideroinkbackend.models.Chat;
import com.example.insideroinkbackend.models.Game;
import com.example.insideroinkbackend.models.Guess;
import com.example.insideroinkbackend.repositories.ChatRepository;
import com.example.insideroinkbackend.repositories.GameRepository;
import com.example.insideroinkbackend.repositories.GuessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GuessService implements GuessServiceI {
    private final GuessRepository guessRepository;
    private final ChatRepository chatRepository;
    private final GameRepository gameRepository;

    @Override
    public void save(String message, String playerId, String role, Chat chat) {
        Guess guess = Guess.create(message, playerId, role, chat);
        guessRepository.save(guess);
    }

    @Override
    public Guess findById(String id) {
        return guessRepository.findById(id).orElseThrow(() -> new GuessNotFoundException(id));
    }

    @Override
    public void guessWord(Game game, String message, String playerId, String role) {
        Chat chat = chatRepository.findByGame(game).orElseThrow(() -> new GameNotFoundException(game.getId()));
        Guess guess = Guess.create(message, playerId, role, chat);

        if (game.getWordToGuess().equalsIgnoreCase(message)) {
            handleCorrectGuess(game, guess, chat);
        } else {
            handleIncorrectGuess(guess, chat);
        }
    }

    private void handleCorrectGuess(Game game, Guess guess, Chat chat) {
        game.setStatus(Game.Status.COMPLETED);
        guess.setCorrect(true);

        gameRepository.save(game);
        guessRepository.save(guess);

        saveMasterResponse("Yes", chat);
    }

    private void handleIncorrectGuess(Guess guess, Chat chat) {
        guess.setCorrect(false);
        guessRepository.save(guess);

        saveMasterResponse("No", chat);
    }

    private void saveMasterResponse(String message, Chat chat) {
        Guess masterResponse = Guess.create(message, "", "Master", chat);
        guessRepository.save(masterResponse);
    }

}
