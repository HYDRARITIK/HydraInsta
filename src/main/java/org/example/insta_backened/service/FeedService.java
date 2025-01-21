package org.example.insta_backened.service;

import org.example.insta_backened.model.Post;
import org.example.insta_backened.model.User;
import org.example.insta_backened.repository.PostRepository;
import org.example.insta_backened.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class FeedService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;
    public Page<Post> getUserFeed( int page, int size) {

        User _logedIn=userService.LoggedInUser();
        // Get the list of users the current user is following
        List<User> followedUsers = userRepository.findFollowedUsers(_logedIn.getId());

        // Extract user IDs of followed users
        List<Long> followedUserIds = followedUsers.stream().map(User::getId).collect(Collectors.toList());

        // Fetch posts from followed users in reverse chronological order with pagination
        return postRepository.findPostsByFollowedUsers(followedUserIds, PageRequest.of(page, size));
    }
}
