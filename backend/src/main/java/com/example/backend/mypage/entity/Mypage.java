package com.example.backend.mypage.entity;

import com.example.backend.comment_like.CommentLike;
import com.example.backend.post_like.PostLike;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import com.example.backend.user.User;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mypage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER) // Ensures User is loaded with Mypage
    private User user;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")  // 'user'는 PostLike에서 올바른 속성입니다.
//    private List<PostLike> postLikes;
//
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "mypage_id")
//    private List<CommentLike> commentLikes;
//

}
