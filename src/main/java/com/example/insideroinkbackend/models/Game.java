package com.example.insideroinkbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Game {
    @Id
    private String id;
    private String wordToGuess;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Player> players = new ArrayList<>();

    public enum Status {
        NOT_STARTED,
        IN_PROGRESS,
        COMPLETED
    }

    public static Game create(String wordToGuess, Status status) {
        return new Game(
                UUID.randomUUID().toString(),
                wordToGuess,
                status,
                List.of()
        );
    }

}
