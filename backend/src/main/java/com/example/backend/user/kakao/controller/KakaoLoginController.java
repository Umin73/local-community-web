package com.example.backend.user.kakao.controller;

import com.example.backend.signLogin.JwtTokenUtil;
import com.example.backend.user.User;
import com.example.backend.user.UserService;
import com.example.backend.user.kakao.dto.KakaoUserInfoResponseDto;
import com.example.backend.user.kakao.service.KakaoJwtService;
import com.example.backend.user.kakao.service.KakaoService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class KakaoLoginController {
    private final KakaoService kakaoService;
    private final UserService userService;
    private final KakaoJwtService kakaoJwtService;

    @Value("${frontend.url}")
    private String frontendUrl;

    long expireTimeMs = 1000 * 60 * 60; // 60분 유효

    @GetMapping("/callback")
    public ResponseEntity<?> callback(@RequestParam("code") String code, HttpServletResponse response) throws IOException {
        // 1. 카카오 인가 코드 사용해 액세스 토큰 가져옴
        String accessToken = kakaoService.getAccessTokenFromKakao(code);

        // 2. 액세스 토큰으로 사용자 정보 요청
        KakaoUserInfoResponseDto userInfo = kakaoService.getUserInfo(accessToken);

        // 3. 사용자 ID를 기반으로 DB에 사용자 존재 여부 확인
        String kakaoUserId = String.valueOf(userInfo.getId());
        boolean userExist = userService.checkKakaoUserExists(kakaoUserId);

        // 4. JWT 토큰 생성
        String jwtToken;

        // 6. 사용자가 DB에 존재X -> 회원가입 페이지로 리다이렉트
        if(!userExist) {
            jwtToken = kakaoJwtService.generateToken(kakaoUserId);
            URI redirectUri = URI.create(frontendUrl + "/jwt-login/join?token=" + jwtToken);
            return ResponseEntity.status(HttpStatus.FOUND).location(redirectUri).build();
        }

        // 7. DB에 존재하는 사용자

        // 유저 기반으로 토큰 생성
        User user = userService.findByUserId(kakaoUserId);
        jwtToken = JwtTokenUtil.createToken(user.getUserId(), user.getId(), expireTimeMs);

        Cookie cookie = new Cookie("jwtToken", jwtToken);
        cookie.setHttpOnly(true);
        cookie.setMaxAge((int) (expireTimeMs / 1000)); // 쿠키 만료 시간 설정
        cookie.setPath("/");
        response.addCookie(cookie);

        // 캐시 비활성화 헤더 설정
        response.addCookie(cookie);
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        // 메인페이지로 리다이렉트
        URI redirectUri = URI.create(frontendUrl + "/");
        return ResponseEntity.status(HttpStatus.FOUND).location(redirectUri).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String accessToken) {
        try {
            kakaoService.logoutKakaoUser(accessToken);
            return ResponseEntity.ok("카카오 로그아웃 성공");
        } catch (Exception e) {
            log.error("카카오 로그아웃 실패: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("카카오 로그아웃 실패");
        }
    }

    // jwt 해독 api
    @PostMapping("/jwt-decode")
    public ResponseEntity<?> decodeJwt(@RequestParam Map<String, String> request) {
        String token = request.get("token");
        if(token != null) {
            try {
                Claims claims = kakaoJwtService.decodeToken(token);
                String userId = claims.getSubject();
                return ResponseEntity.ok(Map.of("userId", userId));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token is missing");
        }
    }
}