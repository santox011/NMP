package com.example.demo.service;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@Service
public class OTPService {

    private Map<String, String> otpStore = new ConcurrentHashMap<>();

    public String generateOTP(String email) {
        String otp = String.valueOf((int)(Math.random() * 900000) + 100000);
        otpStore.put(email, otp);
        return otp;
    }

    public boolean verifyOTP(String email, String otp) {
        return otp.equals(otpStore.get(email));
    }

    public void clearOTP(String email) {
        otpStore.remove(email);
    }
}