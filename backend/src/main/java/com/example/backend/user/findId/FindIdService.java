package com.example.backend.user.findId;

import com.example.backend.mail.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindIdService {

    private final MailService mailService;

    private static int authCode;

    // 인증번호 보내기
    public void sendCodeToEmail(String toEmail) {
        String title = "[TOWN-IN] 이메일 인증";

        createAuthCode();

        String content = "";
        content += "요청하신 인증 번호입니다.\n";
        content += authCode;

        mailService.sendMail(toEmail, title, content);
    }

    // 랜덤 인증번호 생성
    private static void createAuthCode() {
        authCode = (int) ((Math.random() * (90000)) + 100000);
    }

}
