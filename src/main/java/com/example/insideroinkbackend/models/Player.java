package com.example.insideroinkbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Player {
    @Id
    private String id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean isInsider;
    private boolean isMaster;
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    public enum Role {
        INSIDER,
        MASTER,
        COMMONER
    }

    public static Player create(String name, Game game) {
        return new Player(
                UUID.randomUUID().toString(),
                name,
                Role.COMMONER,
                false,
                false,
                game
        );
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", isInsider=" + isInsider +
                '}';
    }
}
