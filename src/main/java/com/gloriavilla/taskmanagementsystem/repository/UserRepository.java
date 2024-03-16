package com.gloriavilla.taskmanagementsystem.repository;

import com.gloriavilla.taskmanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
