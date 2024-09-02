package com.example.insideroinkbackend.repositories;

import com.example.insideroinkbackend.models.Guess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuessRepository extends JpaRepository<Guess, String> {
}
