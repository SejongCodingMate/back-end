package com.creativesemester.SejongCodingMate.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    public String  createMail(String memberId) {

        String temporaryPassword  = generateTemporaryPassword(8);

        String subject = "AI: Escape - 임시 비밀번호 발급";
        String text = "임시 비밀번호는 " + temporaryPassword + " 입니다.";

        sendEmail(memberId, subject, text);
        return temporaryPassword;
    }

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom(senderEmail);
        mailSender.send(message);
    }

    private String generateTemporaryPassword(int length) {
        // 임시 비밀번호를 무작위 문자열로 생성
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder temporaryPassword = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            temporaryPassword.append(characters.charAt(index));
        }
        return temporaryPassword.toString();
    }
}
