package com.example.hrecruit.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "scores")
public class Scores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double technicalScore;

    @Column(nullable = false)
    private Double communicationScore;

    @Column(nullable = false)
    private Double overallScore;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @OneToOne
    @JoinColumn(name = "interview_id", nullable = false)
    private Interviews interview;
}
