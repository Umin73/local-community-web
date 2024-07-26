package com.example.foundation.signLogin;

import com.amazonaws.services.s3.model.Region;
import com.example.foundation.user.User;
import com.example.foundation.user.UserRole;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JoinRequest {

    @NotBlank(message = "로그인 아이디가 비어있습니다.")
    private String userId;

    @NotBlank(message = "비밀번호가 비어있습니다.")
    private String password;
    private String passwordCheck;

    @NotBlank(message = "닉네임이 비어있습니다.")
    private String nickname;

    private String username;
    private String address;
    private String phone;
    private String email;

    // 비밀번호 암호화 X
    public User toEntity() {
        return User.builder()
                .userId(this.userId)
                .password(this.password)
                .nickname(this.nickname)
                .address(this.address)
                .username(this.username)
                .address(this.address)
                .phone(this.phone)
                .email(this.email)
                .role(UserRole.USER)
//                .region(Region.)
                .build();
    }

    // 비밀번호 암호화
    public User toEntity(String encodedPassword) {
        return User.builder()
                .userId(this.userId)
                .password(encodedPassword)
                .nickname(this.nickname)
                .address(this.address)
                .username(this.username)
                .address(this.address)
                .phone(this.phone)
                .email(this.email)
                .role(UserRole.USER)
//                .region(Region.)
                .build();
    }
}

