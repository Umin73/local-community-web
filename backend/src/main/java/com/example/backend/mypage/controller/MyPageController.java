package com.example.backend.mypage.controller;

import com.example.backend.comment.CommentDto;
import com.example.backend.comment_like.CommentLikeDto;
import com.example.backend.mypage.service.MyPageService;
import com.example.backend.post.Post;
import com.example.backend.post.PostDto;
import com.example.backend.post_like.PostLikeDto;
import com.example.backend.post_scrap.PostScrapDto;
import com.example.backend.user.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mypage")
public class MyPageController {

    private final MyPageService myPageService;

    public MyPageController(MyPageService myPageService) {
        this.myPageService = myPageService;
    }


    @GetMapping("/{userId}/like-posts")
    public List<PostLikeDto> getLikePosts(@PathVariable("userId") Long userId) {
        return myPageService.getLikePostsByUserId(userId);
    }

    @GetMapping("/{userId}/like-comments")
    public List<CommentLikeDto> getLikeComments(@PathVariable("userId") Long userId) {
        return myPageService.getLikeCommentsByUserId(userId);
    }

    @GetMapping("/user")
    public UserDto getUserInfo(@RequestParam("id") Long id) {
        return myPageService.getUserById(id);
    }

    @GetMapping("/posts")
    public List<PostDto> getPostsByUserId(@RequestParam("id") Long userId) {
        return myPageService.getPostsByUserId(userId);
    }

    @GetMapping("/comments")
    public List<CommentDto> getCommentsByUserId(@RequestParam("id") Long userId) {
        return myPageService.getCommentsByUserId(userId);
    }

    @GetMapping("/{userId}/commented-posts")
    public List<PostDto> getCommentedPosts(@PathVariable("userId") Long userId) {
        return myPageService.getCommentedPostsByUserId(userId);
    }



    @GetMapping("/scraps")
    public List<PostScrapDto> getScrappedPostsByUserId(@RequestParam("id") Long userId) {
        return myPageService.getScrappedPostsByUserId(userId);
    }

    @PutMapping("/user/{userId}")
    public UserDto updateUser(@PathVariable("userId")  Long id, @RequestBody UserDto userDto) {
        return myPageService.updateUser(id, userDto);
    }



}