package com.example.backend.signLogin;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/jwt-login")
public class AuthController {

    @GetMapping("/check-auth")
    public ResponseEntity<Map<String, Boolean>> checkAuth(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        boolean isAuth = false;

        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if("jwtToken".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    isAuth = JwtTokenUtil.validateToken(token);
                    break;
                }
            }
        }

        Map<String, Boolean> response = new HashMap<>();
        response.put("isAuth", isAuth);
        return ResponseEntity.ok(response);
    }
}
