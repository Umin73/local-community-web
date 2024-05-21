package com.example.foundation.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    //게시글 관련 repository
    List<Post> findByUserId(Long userId);
}
