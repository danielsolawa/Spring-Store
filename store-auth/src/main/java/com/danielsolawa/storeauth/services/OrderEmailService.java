package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.domain.Order;
import com.danielsolawa.storeauth.domain.User;

import javax.mail.MessagingException;

public interface OrderEmailService {

    void sendEmails(User user, Order order) throws MessagingException, InterruptedException;

}
