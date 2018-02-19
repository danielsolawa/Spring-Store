package com.danielsolawa.storeauth.services;

public interface ActivateAccountService {

    boolean activateAccount(Long userId, String token);
    void createNewToken(Long id);
}
