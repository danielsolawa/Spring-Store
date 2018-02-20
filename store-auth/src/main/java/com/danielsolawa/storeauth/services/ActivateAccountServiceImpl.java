package com.danielsolawa.storeauth.services;


import com.danielsolawa.storeauth.domain.ActivationToken;
import com.danielsolawa.storeauth.domain.User;
import com.danielsolawa.storeauth.dtos.EmailDto;
import com.danielsolawa.storeauth.exceptions.AccountAlreadyActivatedException;
import com.danielsolawa.storeauth.exceptions.ActivateTokenExpiredException;
import com.danielsolawa.storeauth.exceptions.ResourceNotFoundException;
import com.danielsolawa.storeauth.exceptions.TokenMismatchException;
import com.danielsolawa.storeauth.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class ActivateAccountServiceImpl implements ActivateAccountService {


    private final String storeEmail;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public ActivateAccountServiceImpl(@Value("${spring.mail.username}") String storeEmail,
                                      UserRepository userRepository, EmailService emailService) {
        this.storeEmail = storeEmail;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Override
    public void activateAccount(String username, String token) {
        User user =  userRepository.findByUsername(username);

        if(user == null){
            throw new ResourceNotFoundException("User not found");
        }

        if(user.isEnabled()){
            throw new AccountAlreadyActivatedException("Account already activated.");
        }


        activate(user, token);
    }



    @Override
    public void createNewToken(String username) {
        User user =  userRepository.findByUsername(username);

        if(user == null){
            throw new ResourceNotFoundException("User not found");
        }

        ActivationToken activationToken = new ActivationToken();
        activationToken.setToken(UUID.randomUUID().toString());
        activationToken.setExpireDate(LocalDateTime.now().plusDays(2L));
        activationToken.setUser(user);

        user.setActivationToken(activationToken);


        try {
            prepareActivationEmail(userRepository.save(user));
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void prepareActivationEmail(User user) throws MessagingException, InterruptedException {
        emailService.sendEmail(
                EmailDto.builder()
                        .user(user)
                        .from(storeEmail)
                        .to(user.getUsername())
                        .subject("Spring Store Account Activation")
                        .text("Welcome to Spring Store!")
                        .build(), user.getActivationToken().getToken()
        );
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
