package com.example.hrecruit.service;

import com.example.hrecruit.dto.SignupRequest;
import com.example.hrecruit.entity.Users;
import com.example.hrecruit.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignupService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users registerUser(SignupRequest signupRequest) {
        if (usersRepository.findByEmail(signupRequest.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already registered.");
        }

        Users user = new Users();
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setRole("CANDIDATE");

        return usersRepository.save(user);
    }
}
