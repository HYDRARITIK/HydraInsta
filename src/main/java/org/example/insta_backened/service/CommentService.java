package org.example.insta_backened.service;

//package org.example.insta_backend.service;
//
//import org.example.insta_backend.model.Comment;
//import org.example.insta_backend.model.Post;
//import org.example.insta_backend.model.User;
//import org.example.insta_backend.repository.CommentRepository;
//import org.example.insta_backend.repository.PostRepository;
//import org.example.insta_backend.repository.UserRepository;
import org.example.insta_backened.model.Comment;
import org.example.insta_backened.model.Post;
import org.example.insta_backened.model.User;
import org.example.insta_backened.repository.CommentRepository;
import org.example.insta_backened.repository.PostRepository;
import org.example.insta_backened.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    private final UserService userService;

    public CommentService(UserService userService) {
        this.userService = userService;
    }

    // Add a comment to a post
    public Comment addComment(Long postId, String content) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        User user =userService.LoggedInUser();

        Comment comment = new Comment(user,post,content);

        return commentRepository.save(comment);
    }

    // Get all comments for a specific post
    public List<Comment> getCommentsByPost(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    // Get all comments by a specific user
    public List<Comment> getCommentsByUser(Long userId) {
        return commentRepository.findByUserId(userId);
    }

    // Get all users and their comments on a particular post
    public List<String> getUsersAndCommentsByPost(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream()
                .map(comment -> comment.getUser().getUsername() + ": " + comment.getContent())
                .collect(Collectors.toList());
    }

    // Delete a comment
    public void deleteComment(Long commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new RuntimeException("Comment not found");
        }
        commentRepository.deleteById(commentId);
    }
}
