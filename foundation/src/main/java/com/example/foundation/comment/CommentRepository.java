package com.example.foundation.comment;

import com.example.foundation.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostIdAndParentCommentIsNull(Long postId);

    List<Comment> findByParentCommentId(Long parentCommentId);

    List<Comment> findByPostId(Long postId);

}
