package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.domain.Inventory;
import com.danielsolawa.storeauth.domain.User;
import com.danielsolawa.storeauth.dtos.UserDto;
import com.danielsolawa.storeauth.exceptions.ResourceNotFoundException;
import com.danielsolawa.storeauth.exceptions.ResourceAlreadyExistsException;
import com.danielsolawa.storeauth.mappers.UserMapper;
import com.danielsolawa.storeauth.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.*;

public class UserServiceImplTest {

    UserService userService;

    @Mock
    UserRepository userRepository;

    UserMapper userMapper;

    @Mock
    PasswordEncoder passwordEncoder;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        EmailService emailService = new EmailServiceImpl(new JavaMailSenderImpl());
        userMapper = UserMapper.INSTANCE;
        userService = new UserServiceImpl(userRepository, userMapper, emailService, passwordEncoder);

    }

    @Test
    public void getUserList() {
        List<User> users = Arrays.asList(new User(), new User(), new User());

        given(userRepository.findAll()).willReturn(users);

        List<UserDto> userDtos = userService.getUserList();

        assertThat(userDtos, hasSize(3));

        then(userRepository).should().findAll();

    }

    @Test
    public void createUserHappyPath() {
        User user = new User();
        user.setId(1L);
        user.setUsername("John");
        user.setPassword("Snow");

        Inventory inventory = new Inventory();
        inventory.setUser(user);
        user.setInventory(inventory);

        given(userRepository.findByUsername(anyString())).willReturn(null);
        given(userRepository.save(any(User.class))).willReturn(user);

        UserDto userDto = userService.createUser(new UserDto());

        assertThat(user.getId(), equalTo(userDto.getId()));
        assertThat(user.getUsername(), equalTo(userDto.getUsername()));
        assertThat(user.getPassword(), equalTo(userDto.getPassword()));
        assertNotNull(userDto.getInventory());

        then(userRepository).should().findByUsername(anyString());
        then(userRepository).should().save(any(User.class));

    }



    @Test(expected = ResourceAlreadyExistsException.class)
    public void createUserFailure() {

        given(userRepository.save(any(User.class))).willThrow(ResourceAlreadyExistsException.class);

        userService.createUser(new UserDto());

        then(userRepository).should().save(any(User.class));

    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("Tony");
        user.setPassword("Stark");

        given(userRepository.findOne(anyLong())).willReturn(user);
        given(userRepository.save(any(User.class))).willReturn(user);

        UserDto userDto = userService.updateUser(1L, new UserDto());

        assertThat(userDto.getId(), equalTo(user.getId()));
        assertThat(userDto.getUsername(), equalTo(user.getUsername()));
        assertThat(userDto.getPassword(), equalTo(user.getPassword()));

        then(userRepository).should().findOne(anyLong());
        then(userRepository).should().save(any(User.class));

    }

    @Test
    public void getUserByIdHappyPath() {
        User user = new User();
        user.setId(1L);
        user.setUsername("Tom");
        user.setPassword("Hanks");

        given(userRepository.findOne(anyLong())).willReturn(user);

        UserDto userDto = userService.getUserById(1L);

        assertThat(userDto.getId(), equalTo(user.getId()));
        assertThat(userDto.getUsername(), equalTo(user.getUsername()));
        assertThat(userDto.getPassword(), equalTo(user.getPassword()));

        then(userRepository).should().findOne(anyLong());


    }

    @Test(expected = ResourceNotFoundException.class)
    public void getUserByIdFailure() {

        given(userRepository.findOne(anyLong())).willThrow(ResourceNotFoundException.class);

        UserDto userDto = userService.getUserById(1L);


        then(userRepository).should().findOne(anyLong());


    }


    @Test
    public void deleteUserById() {

        userService.deleteUserById(1L);

        then(userRepository).should().delete(anyLong());

    }
}