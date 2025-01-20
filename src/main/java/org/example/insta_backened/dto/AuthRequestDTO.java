package org.example.insta_backened.dto;
//package org.example.insta_backened.controller;
//import lombok.*;
//@Setter
//@Getter
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
public class AuthRequestDTO {

    private String username;

    private String password;

    public AuthRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}