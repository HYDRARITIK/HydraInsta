package org.example.insta_backened.controller;

//package org.example.insta_backend.controller;
//
//import org.example.insta_backend.model.Comment;
//import org.example.insta_backend.service.CommentService;
import org.example.insta_backened.model.Comment;
import org.example.insta_backened.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // Add a comment to a post
    @PostMapping("/{postId}")
    public ResponseEntity<Comment> addComment(@PathVariable Long postId, @RequestBody String commentText) {
        Comment comment = commentService.addComment(postId, commentText);
        return ResponseEntity.ok(comment);
    }

    // Get all comments for a specific post
    @GetMapping("/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable Long postId) {
        List<Comment> comments = commentService.getCommentsByPost(postId);
        return ResponseEntity.ok(comments);
    }

    // Get all comments by a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Comment>> getCommentsByUser(@PathVariable Long userId) {
        List<Comment> comments = commentService.getCommentsByUser(userId);
        return ResponseEntity.ok(comments);
    }

    // Get all users and their comments on a particular post
    @GetMapping("/{postId}/details")
    public ResponseEntity<List<String>> getUsersAndCommentsByPost(@PathVariable Long postId) {
        List<String> usersAndComments = commentService.getUsersAndCommentsByPost(postId);
        return ResponseEntity.ok(usersAndComments);
    }

    // Delete a comment
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
