package com.danielsolawa.storeauth.services;

public interface ActivateAccountService {

    void activateAccount(Long userId, String token);
    void createNewToken(Long id);
}
