package com.james.e_commerce_site_project_user.service;


public interface EmailService {
    public void sendVerificationEmail(String to, String verificationToken);
}
