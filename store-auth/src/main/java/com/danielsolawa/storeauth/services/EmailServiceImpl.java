package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.dtos.EmailDto;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    //private JavaMailSender mailSender;

    @Async
    @Override
    public void sendEmail(EmailDto emailDto) {

    }

    @Async
    @Override
    public void sendEmail(EmailDto emailDto, String additionalInfo) {

    }
}
