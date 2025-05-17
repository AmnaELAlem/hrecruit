package com.example.hrecruit.repository;

import com.example.hrecruit.entity.Interviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterviewRepository extends JpaRepository<Interviews, Long> {
    List<Interviews> findByUser_UserId(Long userId);
    List<Interviews> findByUser_UserIdAndStatus(Long userId, String status);
    List<Interviews> findByCommittee(String committee);
}

