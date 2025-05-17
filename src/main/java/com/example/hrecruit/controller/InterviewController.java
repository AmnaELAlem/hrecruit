package com.example.hrecruit.controller;

import com.example.hrecruit.entity.Interviews;
import com.example.hrecruit.entity.Users;
import com.example.hrecruit.service.InterviewService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/interviews")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @GetMapping("/user")
    public List<Interviews> getInterviews(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        return interviewService.getInterviewsByUserId(userId);
    }

    @PostMapping("/create")
    public String createInterview(
        @RequestParam("userId") Users userId,
        @RequestParam("position") String position,
        @RequestParam("date") String date,
        @RequestParam("time") String time,
        @RequestParam("company") String company,
        HttpServletRequest request
    ) {
        Long committeeId = getCommitteeIdFromCookies(request);
        if (committeeId == null) {
            return "Error: Committee ID not found in cookies.";
        }
    
        Interviews interview = new Interviews();
        interview.setUser(userId);
        interview.setPosition(position);
        interview.setDate(date);
        interview.setTime(time);
        interview.setCompany(company);
        interview.setCommittee(String.valueOf(committeeId));
        interview.setStatus("Scheduled");
    
        interviewService.saveInterview(interview);
    
        return "redirect:/dashboard";
    }

    @GetMapping("/committee")
    public List<Interviews> getInterviewsByCommittee(HttpServletRequest request) {
        Long committeeId = getCommitteeIdFromCookies(request);
        if (committeeId == null) {
            return List.of();
        }
        return interviewService.getInterviewsByCommitteeId(committeeId);
    }

    private Long getCommitteeIdFromCookies(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (var cookie : request.getCookies()) {
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

    @PostMapping("/update-status")
    @ResponseBody
    public String updateInterviewStatus(@RequestBody Map<String, String> payload) {
        Long interviewId = Long.valueOf(payload.get("interviewId"));
        String status = payload.get("status");
    
        try {
            interviewService.updateInterviewStatus(interviewId, status);
            return "Status updated successfully!";
        } catch (Exception e) {
            return "Error updating status: " + e.getMessage();
        }
    }
}
