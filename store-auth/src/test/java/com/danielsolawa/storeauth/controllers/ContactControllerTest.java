package com.danielsolawa.storeauth.controllers;

import com.danielsolawa.storeauth.dtos.ContactDto;
import com.danielsolawa.storeauth.services.ContactService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ContactControllerTest extends AbstractControllerTest {

    MockMvc mockMvc;

    @InjectMocks
    ContactController contactController;

    @Mock
    ContactService contactService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(contactController).build();
    }

    @Test
    public void createToOwner() throws Exception {
        ContactDto contactDto = new ContactDto();
        contactDto.setId(1L);

        given(contactService.createToOwner(anyLong(), any(ContactDto.class))).willReturn(contactDto);

        mockMvc.perform(post(ContactController.BASE_URL + "/to-owner/users/1")
                .accept(MediaType.APPLICATION_JSON)
                .content(asJson(contactDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));

        then(contactService).should().createToOwner(anyLong(), any(ContactDto.class));

    }

    @Test
    public void updateToOwner() throws Exception {
        ContactDto contactDto = new ContactDto();
        contactDto.setId(1L);

        given(contactService.updateConversationToOwner(
                anyLong(), anyString(), any(ContactDto.class))).willReturn(contactDto);

        mockMvc.perform(put(ContactController.BASE_URL +
                "/update-conversation-to-owner/users/1/conversation/conid")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(contactDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));



        then(contactService).should().updateConversationToOwner(anyLong(), anyString(), any(ContactDto.class));
    }

    @Test
    public void updateToCustomer() throws Exception {
        ContactDto contactDto = new ContactDto();
        contactDto.setId(1L);

        given(contactService.updateConversationToCustomer(
                anyLong(), anyString(), any(ContactDto.class))).willReturn(contactDto);

        mockMvc.perform(put(ContactController.BASE_URL +
                "/update-conversation-to-customer/users/1/conversation/conid")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(contactDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));



        then(contactService).should().updateConversationToCustomer(anyLong(), anyString(), any(ContactDto.class));
    }



    @Test
    public void getAll() throws Exception {
        List<ContactDto> contactDtos = new ArrayList<>();
        contactDtos.add(new ContactDto());
        contactDtos.add(new ContactDto());
        contactDtos.add(new ContactDto());

        given(contactService.getAll()).willReturn(contactDtos);

        mockMvc.perform(get(ContactController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contacts", hasSize(3)));

        then(contactService).should().getAll();

    }

    @Test
    public void findByUserId() throws Exception {
        List<ContactDto> contactDtos = new ArrayList<>();
        ContactDto contactDto = new ContactDto();
        contactDto.setId(1L);

        contactDtos.add(contactDto);

        given(contactService.findByUserId(anyLong())).willReturn(contactDtos);

        mockMvc.perform(get(ContactController.BASE_URL + "/find-by-id/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contacts", hasSize(1)));

        then(contactService).should().findByUserId(anyLong());
    }

    @Test
    public void findByUserUsername() throws Exception {
        List<ContactDto> contactDtos = new ArrayList<>();
        ContactDto contactDto = new ContactDto();
        contactDto.setId(1L);

        contactDtos.add(contactDto);

        given(contactService.findByUserUsername(anyString())).willReturn(contactDtos);

        mockMvc.perform(get(ContactController.BASE_URL + "/find-by-username/email")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contacts", hasSize(1)));

        then(contactService).should().findByUserUsername(anyString());
    }
}