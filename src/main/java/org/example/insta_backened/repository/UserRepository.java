package org.example.insta_backened.repository;

//package com.instagram.repository;

//import com.instagram.model.User;
import org.example.insta_backened.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    public User findByUsername(String username);

    @Query("SELECT u.following FROM User u WHERE u.id = :userId")
    List<User> findFollowedUsers(@Param("userId") Long userId);


    @Query("SELECT u FROM User u WHERE u.username LIKE %:username%")
    List<User> searchByUsername(@Param("username") String username);

}
