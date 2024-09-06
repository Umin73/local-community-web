package com.example.backend.post_like;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostLikeController {
    @Autowired
    private PostLikeService postLikeService;

    @PostMapping("/post/like")
    public ResponseEntity<String> likePost(@RequestBody PostLikeRequest postLikeRequest) {
        postLikeService.likePost(postLikeRequest.getUserId(), postLikeRequest.getPostId());
        return ResponseEntity.ok("게시글 좋아요 성공");
    }
}
