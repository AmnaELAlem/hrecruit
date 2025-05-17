package com.example.hrecruit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "interviews")
public class Interviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String time;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String company;

    @Column(nullable = true)
    private String committee;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    public void updateInterviewDetails(String position, String date, String time, String status, String company, String committee) {
        this.position = position;
        this.date = date;
        this.time = time;
        this.status = status;
        this.company = company;
        this.committee = committee;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public boolean isScheduledOrCompleted() {
        return "Scheduled".equalsIgnoreCase(this.status) || "Completed".equalsIgnoreCase(this.status);
    }
}
