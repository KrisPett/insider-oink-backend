package com.example.insideroinkbackend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Guess {
    @Id
    private String id;
    private String message;
    private String playerId;
    private String role;
    private boolean isCorrect;
    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    public static Guess create(String message, String playerId, String role, Chat chat) {
        return new Guess(
                UUID.randomUUID().toString(),
                message,
                playerId,
                role,
                false,
                chat
        );
    }
}
