package com.example.backend.user;

import com.example.backend.mypage.entity.Mypage;
import com.example.backend.post_like.PostLike;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private String userId;
    private String password;
    private String username;
    private String address;
    private String phone;
    private String email;
    private String nickname;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Mypage mypage;


}