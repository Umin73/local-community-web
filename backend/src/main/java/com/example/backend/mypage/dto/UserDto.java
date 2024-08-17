package com.example.backend.mypage.dto;

import com.example.backend.user.User;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
