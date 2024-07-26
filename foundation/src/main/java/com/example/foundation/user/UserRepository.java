package com.example.foundation.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);

    boolean existsByuserId(String userId);
    boolean existsByNickname(String nickname);
    Optional<User> findByuserId(String userId);
    //필요한 reposiotry
}
