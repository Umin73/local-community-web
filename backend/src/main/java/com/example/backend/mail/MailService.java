package com.example.backend.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    public void sendMail(String toEmail, String title, String content) {
        SimpleMailMessage emailForm = createEmailForm(toEmail, title, content);

        System.out.println("emailForm.getFrom() = " + emailForm.getFrom());
        System.out.println("emailForm.getSubject() = " + emailForm.getSubject());
        System.out.println("emailForm.getText() = " + emailForm.getText());
        System.out.println("emailForm.getTo() = " + emailForm.getTo());
        
        try{
            mailSender.send(emailForm);
            System.out.println("메일 보내기 성공");
        } catch (RuntimeException e) {
            log.error("MailService.sendMail 예외 발생: " + e.getMessage());
            System.out.println("메일 보내기 실패");
        }
    }

    private SimpleMailMessage createEmailForm(String toEmail, String title, String content) {

        SimpleMailMessage message = new SimpleMailMessage();
        System.out.println("toEmail = " + toEmail);
        message.setTo(toEmail);
        message.setSubject(title);
        message.setText(content);
        message.setFrom("ddwucomtown@gmail.com");

        return message;
    }

    /*private final JavaMailSender mailSender;
    private static final String senderEmail = "ddwucomtown@gmail.com";
    private static int authNum;

    MimeMessage createMail(String mail) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("[TOWN-IN] 이메일 인증");

            String emailContent = "";
            emailContent += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
            emailContent += "<h1>" + authNum + "</h1>";

            message.setText(emailContent, "UTF-8", "html");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return message;
    }

    // 랜덤 인증번호 생성
    static void createAuthNum() {
        authNum = (int) ((Math.random() * (90000)) + 100000);
    }

    int sendMail(String mail) {
        MimeMessage message = createMail(mail);
        mailSender.send(message);

        return authNum;
    }*/
}
