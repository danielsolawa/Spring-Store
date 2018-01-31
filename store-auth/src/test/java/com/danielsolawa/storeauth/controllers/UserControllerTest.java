package com.danielsolawa.storeauth.controllers;

import com.danielsolawa.storeauth.dtos.UserDto;
import com.danielsolawa.storeauth.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;


import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends AbstractControllerTest{

    MockMvc mockMvc;

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getUserList() throws Exception {
        List<UserDto> users = Arrays.asList(new UserDto(), new UserDto(), new UserDto());

        given(userService.getUserList()).willReturn(users);

        mockMvc.perform(get(UserController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.users", hasSize(3)));

        then(userService).should().getUserList();

    }

    @Test
    public void createUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setUsername("kate@wp.pl");
        userDto.setPassword("passshouldbestrong");

        given(userService.createUser(any(UserDto.class))).willReturn(userDto);

        mockMvc.perform(post(UserController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(userDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.username", equalTo(userDto.getUsername())));

        then(userService).should().createUser(any(UserDto.class));

    }

    @Test
    public void updateUser() throws Exception  {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setUsername("Kate");
        userDto.setPassword("password");

        given(userService.updateUser(anyLong(), any(UserDto.class))).willReturn(userDto);

        mockMvc.perform(put(UserController.BASE_URL + "/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(userDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.username", equalTo(userDto.getUsername())));

        then(userService).should().updateUser(anyLong(), any(UserDto.class));
    }

    @Test
    public void getUserById()throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setUsername("Kate");
        userDto.setPassword("password");

        given(userService.getUserById(anyLong())).willReturn(userDto);

        mockMvc.perform(get(UserController.BASE_URL + "/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.username", equalTo(userDto.getUsername())));

        then(userService).should().getUserById(anyLong());

    }


    @Test
    public void deleteUserById() throws Exception {

        mockMvc.perform(delete(UserController.BASE_URL + "/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        then(userService).should().deleteUserById(anyLong());

    }
}