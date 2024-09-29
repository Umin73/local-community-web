package com.example.backend.mail;

import com.example.backend.user.findId.FindIdService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/jwt-login")
@RequiredArgsConstructor
public class MailController {

    private final FindIdService findIdService;

    @PostMapping("/email/send")
    public ResponseEntity sendMessage(@RequestBody Map<String, String> requestData) {

        String email = requestData.get("email");
        findIdService.sendCodeToEmail(email);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
