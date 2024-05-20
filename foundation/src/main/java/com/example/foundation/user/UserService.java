package com.example.foundation.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    //필요한 service
    @Autowired
    private UserRepository userRepository;

    public boolean authenticate(String userId, String password) {
        User user = userRepository.findByUserId(userId);
        return user != null && user.getPassword().equals(password);
    }
}
