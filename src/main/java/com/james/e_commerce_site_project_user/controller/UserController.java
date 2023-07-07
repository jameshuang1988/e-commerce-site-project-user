package com.james.e_commerce_site_project_user.controller;

import com.james.e_commerce_site_project_user.dto.UserRegistrationDTO;
import com.james.e_commerce_site_project_user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/signup")
    public String signUp() {
        log.info("to go sign up page...");
        return "signup";
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDTO registrationDTO) {
        // 調用 UserService 的註冊方法
        log.info("register user page...");
        userService.registerUser(registrationDTO);
        return ResponseEntity.ok("User registered successfully");
    }
}