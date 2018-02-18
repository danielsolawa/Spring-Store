package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.domain.Inventory;
import com.danielsolawa.storeauth.domain.Role;
import com.danielsolawa.storeauth.domain.User;
import com.danielsolawa.storeauth.dtos.UserDto;
import com.danielsolawa.storeauth.exceptions.ResourceNotFoundException;
import com.danielsolawa.storeauth.exceptions.ResourceAlreadyExistsException;
import com.danielsolawa.storeauth.mappers.UserMapper;
import com.danielsolawa.storeauth.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper,
                           EmailService emailService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.emailService = emailService;
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

        if(userAlreadyExists(userDto.getUsername())){
            throw new ResourceAlreadyExistsException("User " + userDto.getUsername() + " already exists.");
        }

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto.setRole(Role.USER);


        return saveUserDto(userDto);
    }


    @Transactional
    @Override
    public UserDto updateUser(Long id, UserDto userDto) {


        return saveUserDto(prepareForUpdate(id, userDto));
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
            throw new ResourceNotFoundException("User not found.");
        }

        return userMapper.userToUserDto(user);

    }


    private UserDto prepareForUpdate(Long userId, UserDto userDto){
        User foundUser = userRepository.findOne(userId);

        if(foundUser == null){
            throw new ResourceNotFoundException("User not found.");
        }


        if(userDto.getUsername() != null){
            foundUser.setUsername(userDto.getUsername());
        }

        if(userDto.getPassword() != null){
            foundUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        if(userDto.getRole() != null){
            foundUser.setRole(userDto.getRole());
        }





        return userMapper.userToUserDto(foundUser);
    }


    private boolean userAlreadyExists(String username) {
        User user = userRepository.findByUsername(username);

        if(user != null){
            return true;
        }

        return false;
    }


    private UserDto saveUserDto(UserDto userDto){
        User user = userMapper.userDtoToUser(userDto);

        if(userDto.getInventory() == null){
            Inventory inventory = new Inventory();
            inventory.setUser(user);
            user.setInventory(inventory);
        }

        UserDto savedUser = userMapper.userToUserDto(userRepository.save(user));

        return savedUser;
    }


}
