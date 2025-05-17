package com.example.hrecruit.service;

import com.example.hrecruit.entity.Interviews;
import com.example.hrecruit.repository.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewService {

    @Autowired
    private InterviewRepository interviewRepository;

    public List<Interviews> getInterviewsByUserId(Long userId) {
        List<Interviews> interviews = interviewRepository.findByUser_UserId(userId);
        System.out.println("Fetched interviews for userId " + userId + ": " + interviews);
        return interviews;
    }

    public List<Interviews> getInterviewsByCommitteeId(Long committeeId) {
        List<Interviews> interviews = interviewRepository.findByCommittee(String.valueOf(committeeId));
        System.out.println("Fetched interviews for committeeId " + committeeId + ": " + interviews);
        return interviews;
    }

    public List<Interviews> getAllInterviews() {
        return interviewRepository.findAll();
    }

    public List<Interviews> getCompletedInterviews(Long userId) {
        return interviewRepository.findByUser_UserIdAndStatus(userId, "Completed");
    }

    public void saveInterview(Interviews interview) {
        interviewRepository.save(interview);
    }

    public void updateInterviewStatus(Long interviewId, String newStatus) {
        Interviews interview = interviewRepository.findById(interviewId)
            .orElseThrow(() -> new RuntimeException("Interview not found"));
        interview.setStatus(newStatus);
        interviewRepository.save(interview);
    }       

    public Interviews findById(Long id) {
        return interviewRepository.findById(id).orElse(null);
    }
}
