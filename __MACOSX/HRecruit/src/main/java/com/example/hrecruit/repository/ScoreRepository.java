package com.example.hrecruit.repository;

import com.example.hrecruit.entity.Scores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Scores, Long> {
    List<Scores> findByUser_UserId(Long userId);
}
