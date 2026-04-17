package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    // ✅ REGISTER
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userRepo.save(user);
    }

    // ✅ LOGIN (Email or Mobile)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> req) {

        String email = req.get("email");
        String phone = req.get("phone");
        String password = req.get("password");

        User user;

        try {

            // 🔹 Find user
            if (email != null && !email.trim().isEmpty()) {
                user = userRepo.findByEmail(email.trim())
                        .orElseThrow(() -> new RuntimeException("User not found"));
            } else {
                user = userRepo.findByPhone(phone.trim())
                        .orElseThrow(() -> new RuntimeException("User not found"));
            }

            // 🔹 Password check (IMPORTANT)
            if (!user.getPassword().equals(password)) {
                return ResponseEntity.status(401).body("Invalid password");
            }

            return ResponseEntity.ok(user);

        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid email/mobile or password");
        }
    }
}