package com.example.hrecruit.controller;

import com.example.hrecruit.entity.Scores;
import com.example.hrecruit.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.servlet.http.Cookie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/myScore")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @GetMapping
    public String showScores(Model model, HttpServletRequest request) {
        Long userId = getLoggedInUserId(request);
        if (userId != null) {
            List<Scores> scores = scoreService.getScoresByUserId(userId);
            scores = scores.stream()
                        .filter(score -> "completed".equalsIgnoreCase(score.getInterview().getStatus()))
                        .collect(Collectors.toList());
            model.addAttribute("scores", scores);
        }
        return "myScore";
    }

    @GetMapping("/api/scores")
    @ResponseBody
    public List<Scores> getScores(HttpServletRequest request) {
        Long userId = getLoggedInUserId(request);
        System.out.println("API request for scores with userId: " + userId);

        if (userId != null) {
            List<Scores> completedScores = scoreService.getScoresByUserId(userId).stream()
                    .filter(score -> score.getInterview() != null && 
                                     "completed".equalsIgnoreCase(score.getInterview().getStatus()))
                    .collect(Collectors.toList());
            System.out.println("Returning completed scores: " + completedScores);
            return completedScores;
        }
        return List.of();
    }

    private Long getLoggedInUserId(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("userId".equals(cookie.getName())) {
                    try {
                        return Long.parseLong(cookie.getValue());
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid userId in cookie: " + cookie.getValue());
                    }
                }
            }
        }
        return null;
    }
}
