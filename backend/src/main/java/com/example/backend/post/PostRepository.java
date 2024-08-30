package com.example.backend.post;

import com.example.backend.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    //게시글 관련 repository
    Page<Post> findByTitleContainingOrContentContaining(String keywordTitle, String keywordContent, Pageable pageable);
    Page<Post> findByCategoryId(Long categoryId, Pageable pageable);
    Page<Post> findByCategoryIdAndTitleContainingOrContentContaining(Long categoryId, String keywordTitle, String keywordContent, Pageable pageable);

    List<Post> findByUser(User user);
}
