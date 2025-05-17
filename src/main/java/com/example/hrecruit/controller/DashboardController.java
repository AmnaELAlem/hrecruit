package com.example.hrecruit.controller;

import com.example.hrecruit.entity.Interviews;
import com.example.hrecruit.service.InterviewService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private InterviewService interviewService;

    @GetMapping("/dashboard")
    public String showDashboard(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        Long userId = null;
        String userRole = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userId".equals(cookie.getName())) {
                    userId = Long.valueOf(cookie.getValue());
                } else if ("userRole".equals(cookie.getName())) {
                    userRole = cookie.getValue();
                }
            }
        }

        if (userId == null || userRole == null) {
            return "redirect:/login";
        }

        List<Interviews> interviews;
        if ("RECRUITER".equalsIgnoreCase(userRole)) {
            interviews = interviewService.getInterviewsByCommitteeId(userId);
        } else {
            interviews = interviewService.getInterviewsByUserId(userId);
        }

        System.out.println("Interviews passed to model: " + interviews);
        model.addAttribute("interviews", interviews);

        if ("CANDIDATE".equalsIgnoreCase(userRole)) {
            return "CandidateDashboard";
        } else if ("RECRUITER".equalsIgnoreCase(userRole)) {
            return "RecruiterDashboard";
        }

        return "redirect:/login";
    }
}
