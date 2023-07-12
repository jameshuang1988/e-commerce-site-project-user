package com.james.e_commerce_site_project_user.service;

import com.james.e_commerce_site_project_user.dto.UserRegistrationDTO;
import com.james.e_commerce_site_project_user.dto.UserRegistrationResponseDTO;
import com.james.e_commerce_site_project_user.model.User;

public interface UserService {
    UserRegistrationResponseDTO registerUser(UserRegistrationDTO userRegistrationDTO) throws Exception;
}
