package org.example.insta_backened.repository;

//import com.instagram.model.Post;
import org.example.insta_backened.model.Comment;
import org.example.insta_backened.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostId(Long postId);
}