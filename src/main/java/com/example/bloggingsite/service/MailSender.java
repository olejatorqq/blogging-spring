package com.example.bloggingsite.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSender {

    @Value("${spring.mail.username}")
    private String userName;

    private final JavaMailSender mailSender;

    public MailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(String mailTo, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(userName);
        mailMessage.setTo(mailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }
}