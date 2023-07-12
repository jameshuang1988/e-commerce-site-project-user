package com.james.e_commerce_site_project_user.controller;

import com.james.e_commerce_site_project_user.repository.UserRepository;
import com.james.e_commerce_site_project_user.dto.UserRegistrationDTO;
import com.james.e_commerce_site_project_user.dto.UserRegistrationResponseDTO;
import com.james.e_commerce_site_project_user.service.UserService;
import com.james.e_commerce_site_project_user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/signup")
    public ModelAndView signup() {
        return new ModelAndView("forward:/signup.html");
    }

    @PostMapping("/register")
    public UserRegistrationResponseDTO registerUser(@RequestBody UserRegistrationDTO dto) throws Exception {
        log.info("register user page...");
        return userService.registerUser(dto);
    }

    @GetMapping("/verify")
    public String verifyEmail(@RequestParam String token) {
        User user = userRepository.findByVerificationToken(token);
        if (user != null) {
            user.setIsVerified(true);
            userRepository.save(user);
            return "Email verified successfully";
        } else {
            return "Invalid verification token";
        }
    }
}