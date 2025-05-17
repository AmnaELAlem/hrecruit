package com.example.hrecruit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactUsController {

    @GetMapping("/contact")
    public String showContactUsPage() {
        return "contactUs";
    }
}
