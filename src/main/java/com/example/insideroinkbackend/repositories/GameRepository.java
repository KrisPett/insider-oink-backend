package com.example.insideroinkbackend.repositories;

import com.example.insideroinkbackend.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, String> {
}
