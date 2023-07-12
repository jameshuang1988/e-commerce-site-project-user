package com.james.e_commerce_site_project_user.service.impl;

import com.james.e_commerce_site_project_user.dto.UserRegistrationResponseDTO;
import com.james.e_commerce_site_project_user.model.User;
import com.james.e_commerce_site_project_user.dto.UserRegistrationDTO;
import com.james.e_commerce_site_project_user.repository.UserRepository;

import com.james.e_commerce_site_project_user.service.EmailService;
import com.james.e_commerce_site_project_user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailService emailService;

    public UserRegistrationResponseDTO registerUser(UserRegistrationDTO dto) {
        User existingUser = userRepository.findByUsername(dto.getUsername());
        if (existingUser != null) {
            return new UserRegistrationResponseDTO("Username already exists.", dto.getUsername(), dto.getEmail());
        }
        existingUser = userRepository.findByEmail(dto.getEmail());
        if (existingUser != null) {
            return new UserRegistrationResponseDTO("Email already exists.", dto.getUsername() , dto.getEmail());
        }
        User newUser = new User();
        newUser.setUsername(dto.getUsername());
        newUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        newUser.setEmail(dto.getEmail());
        newUser.setRole("N");
        newUser.setIsVerified(false);
        newUser.setVerificationToken(UUID.randomUUID().toString());
        newUser = userRepository.save(newUser);
        emailService.sendVerificationEmail(newUser.getEmail(), newUser.getVerificationToken());
        return new UserRegistrationResponseDTO("User registered successfully.",newUser.getUsername(), newUser.getEmail() );
    }


    public void sendVerificationEmail(String to, String verificationToken) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Email Verification");
        message.setText("Thank you to register James's platform. To verify your email, click the following link: http://127.0.0.1:8080/api/verify?token=" + verificationToken);
        mailSender.send(message);
    }

}