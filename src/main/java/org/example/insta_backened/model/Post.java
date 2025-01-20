package org.example.insta_backened.model;

//package com.instagram.model;

import jakarta.persistence.*;
//import lombok.*;
//
//import java.time.LocalDateTime;
//
////package com.instagram.model;
//
//import jakarta.persistence.*;
//import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Setter
//@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String caption;
    private String mediaUrl;
    private String musicUrl;
    private String category;

    private Date createdAt;

    @ManyToOne
    private User user;

    public Post() {

    }

    public Long getId() {
        return id;
    }

    public Post(Long id, String caption, String mediaUrl, String musicUrl, String category, Date createdAt, User user, Set<User> likes) {
        this.id = id;
        this.caption = caption;
        this.mediaUrl = mediaUrl;
        this.musicUrl = musicUrl;
        this.category = category;
        this.createdAt = createdAt;
        this.user = user;
        this.likes = likes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<User> getLikes() {
        return likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }

    @ManyToMany
    private Set<User> likes;
}
