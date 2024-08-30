package com.example.backend.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String userId;
    private String username;
    private String address;
    private String phone;
    private String email;
    private String nickname;
}
