package org.example.insta_backened.controller;


import org.example.insta_backened.model.Post;
import org.example.insta_backened.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.createPost(post));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Post> getSpecificPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getSpecificPost(id));
    }

    @GetMapping("/like/{id}")
    public ResponseEntity<Post> like(@PathVariable Long id) {
        return ResponseEntity.ok(postService.like(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Post>> searchPosts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Date startDate,
            @RequestParam(required = false) Date endDate) {
        List<Post> posts = postService.getFilteredPosts(category, startDate, endDate);
        return ResponseEntity.ok(posts);
    }
}
