package com.example.hrecruit.controller;

import com.example.hrecruit.dto.SignupRequest;
import com.example.hrecruit.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    private SignupService signupService;

    @GetMapping
    public String showSignupPage() {
        return "signup";
    }

    @PostMapping
    public RedirectView registerUser(@ModelAttribute SignupRequest signupRequest) {
        try {
            signupService.registerUser(signupRequest);
            return new RedirectView("/login");
        } catch (IllegalArgumentException e) {
            RedirectView redirectView = new RedirectView("/signup");
            redirectView.addStaticAttribute("error", e.getMessage());
            return redirectView;
        }
    }
}
