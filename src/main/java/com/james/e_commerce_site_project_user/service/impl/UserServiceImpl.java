package com.james.e_commerce_site_project_user.service.impl;

import com.james.e_commerce_site_project_user.dto.UserRegistrationDTO;
import com.james.e_commerce_site_project_user.repository.UserRepository;
import com.james.e_commerce_site_project_user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(UserRegistrationDTO registrationDTO) {

    }
}