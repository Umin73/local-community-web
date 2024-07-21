package com.example.foundation.post_like;

import com.example.foundation.post.Post;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostLikeController {
    @Autowired
    private PostLikeService postLikeService;

    @GetMapping("post/{postId}/likes")
    public int getLikeCountByPostId(@PathVariable("postId") Long postId) {
        return postLikeService.getLikeCountByPostId(postId);
    }

    @PostMapping("/post/like")
    public ResponseEntity<String> likePost(@RequestBody PostLikeRequest postLikeRequest, HttpServletRequest request) {
        try {
            PostLike postLike =  postLikeService.likePost(postLikeRequest.getUserId(), postLikeRequest.getPostId());
            return ResponseEntity.ok("좋아요 성공");
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
