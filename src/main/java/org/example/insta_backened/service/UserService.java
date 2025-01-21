package org.example.insta_backened.service;

//package com.instagram.service;

//import com.instagram.model.User;
//import com.instagram.repository.UserRepository;
import org.example.insta_backened.model.Post;
import org.example.insta_backened.model.User;
import org.example.insta_backened.repository.UserRepository;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//package com.instagram.service;

//import com.instagram.model.User;
//import com.instagram.repository.UserRepository;
//import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {
    private final UserRepository userRepository;
//    @Autowired
    private final PasswordEncoder passwordEncoder;



    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findUserById(Long id) {
        return  userRepository.findById(id);
    }

    public User LoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user=  userRepository.findByUsername(currentPrincipalName);
        if (user == null) {
            user = userRepository.findById(2L)
                    .orElseThrow(() -> new RuntimeException("Default user with ID 2 not found in the database"));
        }

        return user;
    }

    public boolean follow_user(Long Id) {
        User _loggedIn=LoggedInUser();
        boolean userExist= findUserById(Id).isPresent();
        if(userExist){
            //add user
            User _toAdd=userRepository.findById(Id).get();
            Set<User> _followers=_loggedIn.getFollowers();
            _followers.add(_toAdd);
            _loggedIn.setFollowers(_followers);
            userRepository.save(_loggedIn);
            return true;
        }

        return false;

    }

    public Set<Post> getLoggedInUserPosts() {
        User _user=LoggedInUser();
        return  _user.getContent();
    }
    public List<User> searchUsersByUsername(String username) {
        return userRepository.searchByUsername(username);
    }
}
