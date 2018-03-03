package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.domain.Contact;

import javax.mail.MessagingException;

public interface ContactEmailService {

    void sendEmailToOwner(Contact contact) throws MessagingException, InterruptedException;
    void sendEmailToCustomer(Contact contact) throws MessagingException, InterruptedException;

}
