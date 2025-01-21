package org.example.insta_backened.controller;

//package com.instagram.controller;

//import com.instagram.model.User;
//import com.instagram.service.UserService;
import org.example.insta_backened.dto.AuthRequestDTO;
import org.example.insta_backened.dto.JwtResponseDTO;
import org.example.insta_backened.model.Post;
import org.example.insta_backened.model.User;
import org.example.insta_backened.service.UserService;
import org.example.insta_backened.util.JWT_Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

//package com.instagram.controller;
//
//import com.instagram.model.User;
//import com.instagram.service.UserService;


@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    @Autowired
    private JWT_Utility jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){
        String _userName=authRequestDTO.getUsername();
        String _Pass=authRequestDTO.getPassword();
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (_userName,_Pass));
        if(authentication.isAuthenticated()){
            JwtResponseDTO _res= new JwtResponseDTO(jwtService.GenerateToken(authRequestDTO.getUsername()));
            return _res;
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/ping")
    public String test() {
        try {
            return "Welcome";
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id).get());
    }

    @GetMapping("/getDetails")
    public  ResponseEntity<User> getAuthenticatedUserDetails(){

        return ResponseEntity.ok(userService.LoggedInUser());
    }

    @GetMapping("/follow/{id}")
    public ResponseEntity<String> followUser(@PathVariable Long id) {
        System.out.println("id-------------<" + id);  // Debug print, consider using logging here
        boolean isSucc = userService.follow_user(id);

        if (!isSucc) {
            // Return bad request with an informative message
            return ResponseEntity.badRequest().body("Failed to follow user with ID " + id);
        }

        // Return success response with a message
        return ResponseEntity.ok("Successfully followed user with ID " + id);
    }
    @GetMapping("/logedPost")
    public ResponseEntity<Set<Post>> getLoggedInUserPosts() {
        return ResponseEntity.ok(userService.getLoggedInUserPosts());
    }
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam String username) {
        List<User> users = userService.searchUsersByUsername(username);
        return ResponseEntity.ok(users);
    }
}
