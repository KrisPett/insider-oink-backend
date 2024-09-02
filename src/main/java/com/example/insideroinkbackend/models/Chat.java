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
public class Chat {
    @Id
    private String id;
    @OneToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    private List<Guess> guesses = new ArrayList<>();

    public static Chat create(Game game) {
        return new Chat(
                UUID.randomUUID().toString(),
                game,
                List.of()
        );
    }
}
