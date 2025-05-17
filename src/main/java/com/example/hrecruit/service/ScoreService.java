package com.example.hrecruit.service;

import com.example.hrecruit.entity.Scores;
import com.example.hrecruit.repository.ScoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;

    public ScoreService(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    public List<Scores> getScoresByUserId(Long userId) {
        return scoreRepository.findByUser_UserId(userId);
    }
}
