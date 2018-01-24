package com.danielsolawa.storeauth.mappers;

import com.danielsolawa.storeauth.domain.User;
import com.danielsolawa.storeauth.dtos.UserDto;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class UserMapperTest {

    UserMapper userMapper;

    @Before
    public void setUp() throws Exception {
        userMapper = UserMapper.INSTANCE;
    }

    @Test
    public void userDtoToUser() {
        UserDto userDto = new UserDto();
        userDto.setUsername("John");
        userDto.setPassword("password");

        User user = userMapper.userDtoToUser(userDto);

        assertThat(userDto.getUsername(), equalTo(user.getUsername()));
        assertThat(userDto.getPassword(), equalTo(user.getPassword()));

    }

    @Test
    public void userToUserDto() {
        User user = new User();
        user.setUsername("Cassie");
        user.setPassword("password2");

        UserDto userDto = userMapper.userToUserDto(user);

        assertThat(user.getUsername(), equalTo(userDto.getUsername()));
        assertThat(user.getPassword(), equalTo(userDto.getPassword()));
    }
}