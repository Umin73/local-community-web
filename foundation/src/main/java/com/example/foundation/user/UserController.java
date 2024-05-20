package com.example.foundation.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    //singup & login 화면 json파일로 주고받는 controller - postman사이트로 확인 완료
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("post/signup")
    public ResponseEntity<String> processSignup(@RequestBody User user) {
        // 사용자 정보 저장
        userRepository.save(user);
        return new ResponseEntity<>("User signed up successfully", HttpStatus.CREATED);
    }

    @PostMapping("post/login")
    public ResponseEntity<String> processLogin(@RequestBody User user) {
        if (userService.authenticate(user.getUserId(), user.getPassword())) {
            // 로그인 성공 시
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            // 로그인 실패 시
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
