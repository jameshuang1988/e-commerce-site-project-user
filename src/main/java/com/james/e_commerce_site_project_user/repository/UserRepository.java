package com.james.e_commerce_site_project_user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.james.e_commerce_site_project_user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}