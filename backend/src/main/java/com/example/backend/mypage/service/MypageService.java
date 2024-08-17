package com.example.backend.mypage.service;

import com.example.backend.mypage.entity.Mypage;
import com.example.backend.mypage.repository.MypageRepository;
import com.example.backend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MypageService {

    private final MypageRepository mypageRepository;

    @Autowired
    public MypageService(MypageRepository mypageRepository) {
        this.mypageRepository = mypageRepository;
    }

    public Mypage getMypageByUser(User user) {
        return mypageRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Mypage not found for user id: " + user.getId()));
    }
}