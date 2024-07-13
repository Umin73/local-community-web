package com.example.foundation.post_image;

import com.example.foundation.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
    List<PostImage> findByPostId(Long postId);
    Optional<PostImage> findByUrl(String url);
    void deleteByUrl(String url);
}