package com.example.foundation.comment_like;

import com.example.foundation.comment.CommentService;
import com.example.foundation.post_like.PostLike;
import com.example.foundation.post_like.PostLikeRequest;
import com.example.foundation.post_like.PostLikeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentLikeController {
    @Autowired
    private CommentLikeService commentLikeService;

    @GetMapping("/comment/{commentId}/likes")
    public int getLikeCountByPostId(@PathVariable("commentId") Long commentId) {
        return commentLikeService.getLikeCountByCommentId(commentId).size();
    }

    @PostMapping("/comment/like")
    public ResponseEntity<CommentLike> likeComment(@RequestBody CommentLikeRequest commentLikeRequest) {
        try {
            CommentLike commentLike =  commentLikeService.likeComment(commentLikeRequest.getUserId(), commentLikeRequest.getCommentId());
            return new ResponseEntity<>(commentLike, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
