package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.dtos.EmailDto;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    @Override
    public void sendEmail(EmailDto emailDto) {

    }

    @Async
    @Override
    public void sendEmail(EmailDto emailDto, String additionalInfo) {

    }
}
