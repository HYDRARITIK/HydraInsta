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

    @Column(unique = true, nullable = false)
    private String caption;
    private String mediaUrl;
    private String musicUrl;

    @Column( nullable = false)
    private String category;

    private Date createdAt=new Date();



    public Post() {

    }

    public Post(String caption, String category) {
        this.caption = caption;
        this.category = category;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @ManyToOne
    private User user; //means many post -one user

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comments;



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
