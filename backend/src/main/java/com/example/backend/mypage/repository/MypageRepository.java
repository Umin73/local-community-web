package com.example.backend.mypage.repository;

import com.example.backend.mypage.entity.Mypage;
import com.example.backend.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MypageRepository extends JpaRepository<Mypage, Long> {
    Optional<Mypage> findByUserId(Long userId);
}