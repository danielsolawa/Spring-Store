package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.domain.ActivationToken;
import com.danielsolawa.storeauth.domain.User;
import com.danielsolawa.storeauth.exceptions.ActivateTokenExpiredException;
import com.danielsolawa.storeauth.exceptions.TokenMismatchException;
import com.danielsolawa.storeauth.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.anyLong;

public class ActivateAccountServiceImplTest {


    ActivateAccountService activateAccountService;

    @Mock
    UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        activateAccountService = new ActivateAccountServiceImpl(userRepository);
    }

    @Test
    public void activateAccountHappyPath() {
        String token = UUID.randomUUID().toString();
        User user = new User();
        user.setId(1L);

        ActivationToken activationToken = new ActivationToken();
        activationToken.setToken(token);
        activationToken.setExpireDate(LocalDateTime.now().plusDays(10L));

        user.setActivationToken(activationToken);

        given(userRepository.findOne(anyLong())).willReturn(user);

        activateAccountService.activateAccount(1L, token);

        then(userRepository).should().findOne(anyLong());

    }

    @Test(expected = ActivateTokenExpiredException.class)
    public void activateAccountTokenExpired() {
        String token = UUID.randomUUID().toString();
        User user = new User();
        user.setId(2L);

        ActivationToken activationToken = new ActivationToken();
        activationToken.setToken(token);
        activationToken.setExpireDate(LocalDateTime.now().minusDays(10L));

        user.setActivationToken(activationToken);

        given(userRepository.findOne(anyLong())).willReturn(user);

        activateAccountService.activateAccount(1L, token);

        then(userRepository).should().findOne(anyLong());
    }

    @Test(expected = TokenMismatchException.class)
    public void activateAccountTokenMismatch() {

        User user = new User();
        user.setId(2L);

        ActivationToken activationToken = new ActivationToken();
        activationToken.setToken(UUID.randomUUID().toString());
        activationToken.setExpireDate(LocalDateTime.now().plusDays(20L));

        user.setActivationToken(activationToken);

        given(userRepository.findOne(anyLong())).willReturn(user);

        activateAccountService.activateAccount(1L, UUID.randomUUID().toString());

        then(userRepository).should().findOne(anyLong());
    }

    @Test
    public void createNewToken() {
    }


}