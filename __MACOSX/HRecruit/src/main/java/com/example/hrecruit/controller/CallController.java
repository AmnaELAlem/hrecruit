package com.example.hrecruit.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/call")
public class CallController {

    @GetMapping("/{candidateId}")
    public String joinCall(@PathVariable Long candidateId, HttpServletRequest request, Model model) {
        Long loggedInUserId = getLoggedInUserId(request);
        if (loggedInUserId == null) {
            return "redirect:/dashboard"; // Redirect if user is not logged in
        }
    
        // Unique room name logic
        String roomName = "room_" + loggedInUserId + "_" + candidateId;
        model.addAttribute("roomName", roomName);
    
        return "call"; // Render call.html
    }    

    private Long getLoggedInUserId(HttpServletRequest request) {
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
}
