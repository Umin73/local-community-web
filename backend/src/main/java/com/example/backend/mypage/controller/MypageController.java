package com.example.backend.mypage.controller;

import com.example.backend.mypage.ResourceNotFoundException;
import com.example.backend.mypage.dto.MypageDto;
import com.example.backend.mypage.entity.Mypage;
import com.example.backend.mypage.service.MypageService;
import com.example.backend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/mypage")
public class MypageController {

    private final MypageService mypageService;

    @Autowired
    public MypageController(MypageService mypageService) {
        this.mypageService = mypageService;
    }

    @GetMapping
    public MypageDto getMypage(@RequestParam(name = "userId") Long userId) {
        User user = new User();
        user.setId(userId);
        Mypage mypage = mypageService.getMypageByUser(user);
        return new MypageDto(mypage);
    }

}