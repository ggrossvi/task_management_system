package com.gloriavilla.taskmanagementsystem.repository;

import com.gloriavilla.taskmanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    Optional<User> findById(Long id);
    User findByName(String name);
}
