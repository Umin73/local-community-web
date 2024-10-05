package com.example.backend.user.findId;

import com.example.backend.mail.MailController;
import com.example.backend.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/jwt-login")
@RequiredArgsConstructor
public class FindIdController {
    private final UserService userService;
    private final MailController mailController;
    private final FindIdService findIdService;

    @GetMapping("/exist-email")
    public Map<String, Object> checkEmail(@RequestParam(name = "email") String email) {
        Map<String, Object> response = new HashMap<>();

        if(userService.checkUserEmailExists(email)) {
            response.put("success", true);
            response.put("message", "인증번호가 이메일로 전송되었습니다.");

        } else {
            response.put("success", false);
            response.put("message", "등록되지 않은 이메일입니다.");
        }

        return response;
    }

    @GetMapping("/find-id")
    public Map<String, Object> findId(@RequestParam(name = "email") String email) {
        Map<String, Object> response = new HashMap<>();

        try{
            String userId = userService.findUserIdByEmail(email);
            response.put("success", true);
            response.put("userId", userId);
        } catch (RuntimeException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }

        return response;
    }

    @GetMapping("/exist-id")
    public Map<String, Object> checkId(@RequestParam(name = "id") String id) {
        Map<String, Object> response = new HashMap<>();

        if(userService.checkUserIdExists(id)) {
            response.put("success", true);
            response.put("message", "비밀번호 변경 링크가 계정에 등록된 이메일로 전송되었습니다.");
        } else {
            response.put("success", false);
            response.put("message", "등록되지 않은 아이디입니다.");
        }

        return response;
    }

    @PostMapping("/get-email")
    public Map<String, Object> getEmail(@RequestBody Map<String, String> requestData) {
        Map<String, Object> response = new HashMap<>();
        String id = requestData.get("id");

        try {
            String email = userService.findEmailByUserId(id);
            response.put("success", true);
            response.put("email", email);
        } catch (RuntimeException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }

        return response;
    }

}