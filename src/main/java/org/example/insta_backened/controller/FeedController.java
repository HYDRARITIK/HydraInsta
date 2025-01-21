package org.example.insta_backened.controller;


import org.example.insta_backened.model.Post;
import org.example.insta_backened.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/feed")
public class FeedController {

    @Autowired
    private FeedService feedService;

    @GetMapping("/{userId}")
    public ResponseEntity<Page<Post>> getUserFeed(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Post> feed = feedService.getUserFeed(page, size);
        return ResponseEntity.ok(feed);
    }
}
