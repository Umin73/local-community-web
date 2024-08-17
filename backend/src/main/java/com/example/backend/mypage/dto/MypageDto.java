package com.example.backend.mypage.dto;

import com.example.backend.mypage.entity.Mypage;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MypageDto {
    private Long id;
    private UserDto user;
//    private List<PostDto> likedPosts;

    public MypageDto(Mypage mypage) {
        this.id = mypage.getId();
        this.user = new UserDto(mypage.getUser());
//        this.likedPosts = mypage.getPostLikes().stream()
//                .map(postLike -> new PostDto(postLike.getPost().getId(), postLike.getPost().getContent()))
//                .collect(Collectors.toList());
    }
}