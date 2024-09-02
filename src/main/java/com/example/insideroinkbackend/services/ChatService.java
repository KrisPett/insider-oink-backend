package com.example.insideroinkbackend.services;

import com.example.insideroinkbackend.exceptions.ChatNotFoundException;
import com.example.insideroinkbackend.exceptions.GameNotFoundException;
import com.example.insideroinkbackend.models.Chat;
import com.example.insideroinkbackend.models.Game;
import com.example.insideroinkbackend.repositories.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService implements ChatServiceI {
    private final ChatRepository chatRepository;

    @Override
    public void save(Game game) {
        chatRepository.findByGame(game)
                .ifPresentOrElse(
                        _ -> System.out.println("Chat already exists for this game."),
                        () -> chatRepository.save(Chat.create(game))
                );
    }

    @Override
    public Chat findById(String id) {
        return chatRepository.findById(id).orElseThrow(() -> new ChatNotFoundException(id));
    }

    @Override
    public Chat findByGame(Game game) {
        Chat chat = chatRepository.findByGame(game).orElseThrow(() -> new GameNotFoundException(game.getId()));
        return chatRepository.findById(chat.getId()).orElseThrow(() -> new ChatNotFoundException(chat.getId()));
    }
}
