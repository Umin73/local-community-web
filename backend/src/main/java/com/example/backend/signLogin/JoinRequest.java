package com.example.backend.signLogin;

import com.example.backend.user.User;
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
    private String nickname;
    private String username;
    private String address;
    private String phone;
    private String email;


    public User toEntity() {
        return User.builder()
                .userId(this.userId)
                .password(this.password)
                .username(this.username)
                .nickname(this.nickname)
                .address(this.address)
                .phone(this.phone)
                .email(this.email)
                .profile_url("https://image11.coupangcdn.com/image/cmg/oms/banner/e587245b-b580-48da-84b6-ac5f6aa392fb_980x670.jpg") // Default profile picture URL
                .build();
    }

    public User toEntity(String encodedPassword) {
        return User.builder()
                .userId(this.userId)
                .password(encodedPassword)
                .username(this.username)
                .nickname(this.nickname)
                .address(this.address)
                .phone(this.phone)
                .email(this.email)
                .profile_url("https://image11.coupangcdn.com/image/cmg/oms/banner/e587245b-b580-48da-84b6-ac5f6aa392fb_980x670.jpg") // Default profile picture URL
                .build();
    }
}