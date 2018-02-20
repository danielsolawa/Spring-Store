package com.danielsolawa.storeauth.controllers;

import com.danielsolawa.storeauth.services.ActivateAccountService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ActivateAccountControllerTest {

    MockMvc mockMvc;

    @Mock
    ActivateAccountService activateAccountService;

    ActivateAccountController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new ActivateAccountController(activateAccountService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void activateAccount() throws Exception {
        mockMvc.perform(get
                (ActivateAccountController.BASE_URL + "/user@user.com/activate/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        then(activateAccountService).should().activateAccount(anyString(), anyString());

    }

    @Test
    public void createNewToken() throws Exception {
        mockMvc.perform(get
                (ActivateAccountController.BASE_URL  + "/user@user.com/create-new-token/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        then(activateAccountService).should().createNewToken(anyString());
    }
}