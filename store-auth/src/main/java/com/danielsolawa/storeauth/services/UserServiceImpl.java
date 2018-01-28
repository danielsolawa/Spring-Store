package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.domain.Inventory;
import com.danielsolawa.storeauth.domain.User;
import com.danielsolawa.storeauth.dtos.UserDto;
import com.danielsolawa.storeauth.exceptions.ResourceNotFoundException;
import com.danielsolawa.storeauth.mappers.UserMapper;
import com.danielsolawa.storeauth.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDto> getUserList() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> userMapper.userToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return saveUserDto(userDto);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        userDto.setId(id);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        

        return saveUserDto(userDto);
    }


    @Override
    public UserDto getUserById(Long id) {

        return findUserDtoById(id);
    }


    @Override
    public void deleteUserById(Long id) {
        userRepository.delete(id);
    }


    private UserDto findUserDtoById(Long id) {
        User user = userRepository.findOne(id);

        if(user == null){
            throw new ResourceNotFoundException();
        }

        return userMapper.userToUserDto(user);

    }

    private UserDto saveUserDto(UserDto userDto){
        User user = userMapper.userDtoToUser(userDto);

        if(userDto.getInventory() == null){
            Inventory inventory = new Inventory();
            inventory.setUser(user);
            user.setInventory(inventory);
        }

        return userMapper.userToUserDto(userRepository.save(user));
    }


}
