package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.dtos.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getUserList();
    List<UserDto> getUserList(Integer start, Integer end);
    UserDto createUser(UserDto userDto);
    UserDto updateUser(Long id, UserDto userDto);
    UserDto getUserById(Long id);
    Long getUserListSize();
    void deleteUserById(Long id);
}
