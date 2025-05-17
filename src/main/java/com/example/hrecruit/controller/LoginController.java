package com.example.hrecruit.controller;

import com.example.hrecruit.entity.Users;
import com.example.hrecruit.service.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public RedirectView loginUser(@RequestParam String email, @RequestParam String password, HttpServletResponse response) {
        try {
            Users authenticatedUser = loginService.authenticateUser(email, password);

            Cookie userIdCookie = new Cookie("userId", String.valueOf(authenticatedUser.getUserId()));
            Cookie userRoleCookie = new Cookie("userRole", authenticatedUser.getRole());

            userIdCookie.setHttpOnly(true);
            userIdCookie.setPath("/");
            userRoleCookie.setHttpOnly(true);
            userRoleCookie.setPath("/");

            response.addCookie(userIdCookie);
            response.addCookie(userRoleCookie);

            return new RedirectView("/dashboard");
        } catch (IllegalArgumentException e) {
            RedirectView redirectView = new RedirectView("/login");
            redirectView.addStaticAttribute("error", e.getMessage());
            return redirectView;
        }
    }
}
