package com.example.foundation.user;

import com.example.foundation.signLogin.JoinRequest;
import com.example.foundation.signLogin.LoginRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // Spring Security를 사용한 로그인 구현 시 사용
//    private final PasswordEncoder encoder;
    private final BCryptPasswordEncoder encoder;

    public boolean authenticate(String userId, String password) {
        User user = userRepository.findByUserId(userId);
        return user != null && user.getPassword().equals(password);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * userId 중복 체크
     * 회원가입 기능 구현 시 사용
     * 중복되면 true return
     */
    public boolean checkuserIdDuplicate(String userId) {
        return userRepository.existsByuserId(userId);
    }

    /**
     * nickname 중복 체크
     * 회원가입 기능 구현 시 사용
     * 중복되면 true return
     */
    public boolean checkNicknameDuplicate(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    /**
     * 회원가입 기능 1
     * 화면에서 JoinRequest(userId, password, nickname)을 입력받아 User로 변환 후 저장
     * userId, nickname 중복 체크는 Controller에서 진행 => 에러 메세지 출력을 위해
     */
    public void join(JoinRequest req) {
        userRepository.save(req.toEntity());
    }

    /**
     * 회원가입 기능 2
     * 화면에서 JoinRequest(userId, password, nickname)을 입력받아 User로 변환 후 저장
     * 회원가입 1과는 달리 비밀번호를 암호화해서 저장
     * userId, nickname 중복 체크는 Controller에서 진행 => 에러 메세지 출력을 위해
     */
    public void join2(JoinRequest req) {
        userRepository.save(req.toEntity(encoder.encode(req.getPassword())));
    }

    /**
     *  로그인 기능
     *  화면에서 LoginRequest(userId, password)을 입력받아 userId와 password가 일치하면 User return
     *  userId가 존재하지 않거나 password가 일치하지 않으면 null return
     */
    
    public User login(LoginRequest req) {
        Optional<User> optionalUser = userRepository.findByuserId(req.getUserId());

        if (optionalUser.isEmpty()) {
            System.out.println("Login ID not found");
            return null;
        }

        User user = optionalUser.get();
        System.out.println("User found: " + user.getUserId());

        if (!encoder.matches(req.getPassword(), user.getPassword())) {
            System.out.println("Password does not match");
            return null;
        }

        System.out.println("Password matches");
        return user;
    }

    /**
     * userId(Long)를 입력받아 User을 return 해주는 기능
     * 인증, 인가 시 사용
     * userId가 null이거나(로그인 X) userId로 찾아온 User가 없으면 null return
     * userId로 찾아온 User가 존재하면 User return
     */

    public User getLoginUser(Long userId) {
        return getLoginUserById(userId);
    }

    public User getLoginUserById(Long userId) {
        if(userId == null) return null;

        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) return null;

        return optionalUser.get();
    }

    /**
     * userId(String)를 입력받아 User을 return 해주는 기능
     * 인증, 인가 시 사용
     * userId가 null이거나(로그인 X) userId로 찾아온 User가 없으면 null return
     * userId로 찾아온 User가 존재하면 User return
     */
    public User getLoginUserByuserId(String userId) {
        if(userId == null) return null;

        Optional<User> optionalUser = userRepository.findByuserId(userId);
        if(optionalUser.isEmpty()) return null;

        return optionalUser.get();
    }
}