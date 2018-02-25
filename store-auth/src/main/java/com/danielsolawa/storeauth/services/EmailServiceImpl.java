package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.dtos.EmailDto;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    @Override
    public void sendEmail(EmailDto emailDto) throws InterruptedException, MessagingException {
        Thread.sleep(10000);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper =
                new MimeMessageHelper(mimeMessage, true, "UTF-8");

        mimeMessageHelper.setFrom(emailDto.getFrom());
        mimeMessageHelper.setTo(emailDto.getTo());
        mimeMessageHelper.setSubject(emailDto.getSubject());
        mimeMessageHelper.setText(emailDto.getText(), true);

        mailSender.send(mimeMessage);

    }

    @Async
    @Override
    public void sendEmail(EmailDto emailDto, String additionalInfo) throws MessagingException, InterruptedException {
        Thread.sleep(10000);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper =
                new MimeMessageHelper(mimeMessage , true, "UTF-8");

        mimeMessageHelper.setFrom(emailDto.getFrom());
        mimeMessageHelper.setTo(emailDto.getTo());
        mimeMessageHelper.setSubject(emailDto.getSubject());
        mimeMessageHelper.setText(buildMessage(emailDto, additionalInfo));

        mailSender.send(mimeMessage);
    }

    private String buildMessage(EmailDto emailDto, String token){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(emailDto.getText() + "\n");
        stringBuilder.append("http://localhost:9000/#!/users/");
        stringBuilder.append(emailDto.getUser().getUsername());
        stringBuilder.append("/activate/");
        stringBuilder.append(token);

        return stringBuilder.toString();
    }
}
