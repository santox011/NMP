package com.example.demo.service;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOTP(String to, String otp) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject("NearMyPark Password Reset OTP");
        msg.setText("Your OTP is: " + otp);

        mailSender.send(msg);
    }
}