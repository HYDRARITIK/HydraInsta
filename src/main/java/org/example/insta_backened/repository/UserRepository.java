package org.example.insta_backened.repository;

//package com.instagram.repository;

//import com.instagram.model.User;
import org.example.insta_backened.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    public User findByUsername(String username);
}
