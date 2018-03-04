package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.domain.Contact;
import com.danielsolawa.storeauth.dtos.EmailDto;
import com.danielsolawa.storeauth.utils.EmailTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@Slf4j
public class ContactEmailServiceImpl implements ContactEmailService {

    private final String ownerEmail;
    private final EmailService emailService;
    private final EmailTemplate emailTemplate;


    public ContactEmailServiceImpl(@Value("${spring.mail.username}") String ownerEmail,
                                   EmailService emailService, EmailTemplate emailTemplate) {
        this.ownerEmail = ownerEmail;
        this.emailService = emailService;
        this.emailTemplate = emailTemplate;
    }


    @Override
    public void sendEmailToOwner(Contact contact) throws MessagingException, InterruptedException {
        String message = generateMessage(contact, true);

        log.info("sending an email to " + ownerEmail);
        emailService.sendEmail(
                EmailDto
                        .builder()
                        .subject(contact.getSubject())
                        .text(message)
                        .from(ownerEmail)
                        .to(ownerEmail)
                        .build()
        );

    }


    @Override
    public void sendEmailToCustomer(Contact contact) throws MessagingException, InterruptedException {
        String message = generateMessage(contact, true);

        emailService.sendEmail(
                EmailDto
                        .builder()
                        .subject(contact.getSubject())
                        .text(message)
                        .from(ownerEmail)
                        .to(contact.getUsers().get(0).getUsername())
                        .build()
        );
    }


    private String generateMessage(Contact contact, boolean toOwner) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(emailTemplate.getMessageStart());
        if(toOwner){
            stringBuilder.append(emailTemplate.generateContactMessageBodyForOwner(contact));
        }else{
            stringBuilder.append(emailTemplate.generateContactMessageBodyForCustomer(contact));
        }


        return stringBuilder.toString();
    }
}
