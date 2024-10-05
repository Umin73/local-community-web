package com.example.backend.mail;

import com.example.backend.config.RedisService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private final RedisService redisService;

    private static String senderEmail = "comtownddwu@naver.com";
    private static int number;
    private static String pwLink = "~~~";

    public static void createNumber() {
        number = (int)(Math.random()*90000) + 100000;
    }

    public MimeMessage createFindIdMail(String mail) {

        createNumber();

        MimeMessage message = javaMailSender.createMimeMessage();

        try{
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("[TOWN-IN] 이메일 인증");

            String body = "";
            body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
            body += "<h1>" + number + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";

            message.setText(body,"UTF-8", "html");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return message;
    }

    public MimeMessage createFindPwMail(String mail) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try{
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("[TOWN-IN] 비밀번호 재설정 링크");

            String body = "";
            body += "<h3>" + "요청하신 비밀번호 재설정 링크입니다." + "</h3>";
            body += "<h1>" + pwLink + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";

            message.setText(body,"UTF-8", "html");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return message;
    }

    public int sendMail(MailDto mailDto) {
        String mail = mailDto.getEmail().trim();
        MimeMessage message = switch (mailDto.getType()) {
            case "findId" -> createFindIdMail(mail);
            case "findPw" -> createFindPwMail(mail);
            default ->
                    throw new IllegalArgumentException("유효하지 않은 메일 타입입니다: " + mailDto.getType());
        };

        javaMailSender.send(message);
        redisService.save(mail, String.valueOf(number));
        return number;
    }

    public boolean verifyCode(ApproveRequestDto approveRequestDto) {
        String storedCode = redisService.get(approveRequestDto.getEmail());

        if(storedCode != null && storedCode.equals(approveRequestDto.getCode())) {
            redisService.delete(approveRequestDto.getEmail());
            return true;
        } else {
            return false;
        }
    }
}
