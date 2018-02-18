package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.dtos.EmailDto;

import javax.mail.MessagingException;

public interface EmailService {

    void sendEmail(EmailDto emailDto);
    void sendEmail(EmailDto emailDto, String additionalInfo) throws MessagingException, InterruptedException;
}
