package com.example.backend.post_scrap;

import com.example.backend.user.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostScrapRepository extends JpaRepository<PostScrap, Long> {
    List<PostScrap> findByPostId(Long postId);

    Optional<PostScrap> findByUserIdAndPostId(Long userId, Long postId);
    @Transactional
    void deleteByUserIdAndPostId(Long userId, Long postId);
    List<PostScrap> findByUser(User user);
    Page<PostScrap> findByUser(User user, Pageable pageable);
}
