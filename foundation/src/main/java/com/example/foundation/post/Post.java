package com.example.foundation.post;

import com.example.foundation.user.User;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    //우선적으로 연결성 확인
    private String title;
    private String content;

//    private LocalDateTime createdTime;
//    private LocalDateTime modifiedTime;
//    private int likeCount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post() {

    }

    public Post(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

//    public LocalDateTime getCreatedTime() {
//        return createdTime;
//    }
//
//    public void setCreatedTime(LocalDateTime createdTime) {
//        this.createdTime = createdTime;
//    }
//
//    public LocalDateTime getModifiedTime() {
//        return modifiedTime;
//    }
//
//    public void setModifiedTime(LocalDateTime modifiedTime) {
//        this.modifiedTime = modifiedTime;
//    }
//
//    public int getLikeCount() {
//        return likeCount;
//    }
//
//    public void setLikeCount(int likeCount) {
//        this.likeCount = likeCount;
//    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }




}
