package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.dtos.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getUserList();
    UserDto createUser(UserDto userDto);
    UserDto updateUser(Long id, UserDto userDto);
    UserDto getUserById(Long id);
}
