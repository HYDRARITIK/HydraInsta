package org.example.insta_backened.repository;

//package com.instagram.repository;

//import com.instagram.model.Post;
import org.example.insta_backened.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUserId(Long userId);
}
