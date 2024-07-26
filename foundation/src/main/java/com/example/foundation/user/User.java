package com.example.foundation.user;

import com.example.foundation.region.Region;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String password;
    private String username;
    private String address;
    private String phone;
    private String email;
    private String nickname;
    private UserRole role;

//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    private Region region;


}