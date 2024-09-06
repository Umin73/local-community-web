package com.example.backend.post_scrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostScrapController {
    @Autowired
    private PostScrapService postScrapService;

    @GetMapping("post/{postId}/scraps")
    public int getScrapCountByPostId(@PathVariable("postId") Long postId) {
        return postScrapService.getScrapCountByPostId(postId);
    }

    @PostMapping("/post/scrap")
    public ResponseEntity<String> scrapPost(@RequestBody PostScrapRequest request) {
        postScrapService.scrapPost(request.getUserId(), request.getPostId());
        return ResponseEntity.ok("스크랩 성공");
    }

    @PostMapping("/post/unscrap")
    public ResponseEntity<String> unscrapPost(@RequestBody PostScrapRequest request) {
        postScrapService.unscrapPost(request.getUserId(), request.getPostId());
        return ResponseEntity.ok("스크랩 취소 성공");
    }
}
