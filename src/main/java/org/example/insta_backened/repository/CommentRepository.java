package org.example.insta_backened.repository;

//package org.example.insta_backend.repository;
//
//import org.example.insta_backend.model.Comment;
import org.example.insta_backened.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
    List<Comment> findByUserId(Long userId);
}
