package com.example.insideroinkbackend.repositories;

import com.example.insideroinkbackend.models.Chat;
import com.example.insideroinkbackend.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, String> {
    Optional<Chat> findByGame(Game game);
}
