package com.example.backend.post_scrap;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostScrapDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String userName;
    private int likeCount;
    private int commentCount;


    public PostScrapDto(Long id, String title, String content, LocalDateTime createdDate, LocalDateTime modifiedDate, String userName, int likeCount,int commentCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.userName = userName;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
    }
}
