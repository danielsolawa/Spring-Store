package com.danielsolawa.storeauth.services;

public interface ActivateAccountService {

    void activateAccount(String username, String token);
    void createNewToken(String username);
}
