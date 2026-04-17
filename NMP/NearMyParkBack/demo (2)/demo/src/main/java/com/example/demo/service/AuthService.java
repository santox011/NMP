package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.UserRepository;
import com.example.demo.entity.User;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepo;

    public User login(String email, String phone, String password) {

        User user;

        if (email != null && !email.isEmpty()) {
            user = userRepo.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));
        } else {
            user = userRepo.findByPhone(phone)
                    .orElseThrow(() -> new RuntimeException("User not found"));
        }

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
}