package org.example.insta_backened.service;

//package com.instagram.service;
//
//import com.instagram.model.Post;
//import com.instagram.repository.PostRepository;
import org.example.insta_backened.model.Post;
import org.example.insta_backened.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

//package com.instagram.service;
//
//import com.instagram.model.Post;
//import com.instagram.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(Post post) {
//        post.setCreatedAt(new Date());
        return postRepository.save(post);
    }

    public List<Post> getUserPosts(Long userId) {
        return postRepository.findAllByUserId(userId);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
