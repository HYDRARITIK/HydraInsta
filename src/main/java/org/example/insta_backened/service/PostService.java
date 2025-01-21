package org.example.insta_backened.service;

//package com.instagram.service;
//
//import com.instagram.model.Post;
//import com.instagram.repository.PostRepository;
import org.example.insta_backened.model.Post;
import org.example.insta_backened.model.User;
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
import java.util.Set;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public Post createPost(Post post) {
//        post.setCreatedAt(new Date());
        User _loginUser=userService.LoggedInUser();
        post.setUser(_loginUser);
        return postRepository.save(post);
    }

    public List<Post> getUserPosts(Long userId) {
        return postRepository.findAllByUserId(userId);
    }

    public List<Post> getAllPosts() {

        return postRepository.findAll();
    }


    public Post getSpecificPost(Long id) {
        return postRepository.findById(id).get();
    }

    public Post like(Long id) {
        Post _post=postRepository.findById(id).get();
        Set<User> likes=  _post.getLikes();
        User _loginUser=userService.LoggedInUser();
        likes.add(_loginUser);
        _post.setLikes(likes);
        postRepository.save(_post);
        return _post;

    }

    public List<Post> getFilteredPosts(String category, Date startDate, Date endDate) {
        return postRepository.findPostsByFilters(category, startDate, endDate);
    }
}
