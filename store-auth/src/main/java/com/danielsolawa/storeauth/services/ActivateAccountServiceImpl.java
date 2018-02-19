package com.danielsolawa.storeauth.services;


import com.danielsolawa.storeauth.domain.ActivationToken;
import com.danielsolawa.storeauth.domain.User;
import com.danielsolawa.storeauth.exceptions.ResourceNotFoundException;
import com.danielsolawa.storeauth.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class ActivateAccountServiceImpl implements ActivateAccountService {

    private final UserRepository userRepository;

    public ActivateAccountServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean activateAccount(Long userId, String token) {
        User user =  userRepository.findOne(userId);

        if(user == null){
            throw new ResourceNotFoundException("User not found");
        }



        return activate(user.getActivationToken(), token);
    }



    @Override
    public void createNewToken(Long id) {

    }

    private boolean activate(ActivationToken activationToken , String token) {
        if(LocalDateTime.now().isAfter(activationToken.getExpireDate())){

        }

        return false;

    }
}
