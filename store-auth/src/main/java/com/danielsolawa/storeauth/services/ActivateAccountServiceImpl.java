package com.danielsolawa.storeauth.services;


import com.danielsolawa.storeauth.domain.ActivationToken;
import com.danielsolawa.storeauth.domain.User;
import com.danielsolawa.storeauth.exceptions.ActivateTokenExpiredException;
import com.danielsolawa.storeauth.exceptions.ResourceNotFoundException;
import com.danielsolawa.storeauth.exceptions.TokenMismatchException;
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
    public void activateAccount(String username, String token) {
        User user =  userRepository.findByUsername(username);

        if(user == null){
            throw new ResourceNotFoundException("User not found");
        }


        activate(user, token);
    }



    @Override
    public void createNewToken(Long id) {

    }

    private void activate(User user , String token) {
        ActivationToken activationToken = user.getActivationToken();

        if(LocalDateTime.now().isAfter(activationToken.getExpireDate())){
            throw new ActivateTokenExpiredException("Activation Token has expired");
        }

        if(!activationToken.getToken().equals(token)){
            throw new TokenMismatchException("Wrong Token");
        }


        user.setEnabled(true);
        userRepository.save(user);

    }
}
