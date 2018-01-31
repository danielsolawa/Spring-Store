package com.danielsolawa.storeauth.controllers;


import com.danielsolawa.storeauth.dtos.UserDto;
import com.danielsolawa.storeauth.dtos.UserListDto;
import com.danielsolawa.storeauth.exceptions.ValidationConstraintException;
import com.danielsolawa.storeauth.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {

    public static final String BASE_URL = "/api/v1/users";

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    //@PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserListDto getUserList(){
        return new UserListDto(userService.getUserList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody  UserDto userDto, Errors errors){
        checkValidation(errors);

        return userService.createUser(userDto);
    }



    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto){


        return userService.updateUser(id, userDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserById(@PathVariable Long id){

        userService.deleteUserById(id);
    }


    private void checkValidation(Errors errors) {
        if(errors.hasErrors()){
            String message = errors.getAllErrors()
                    .stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.joining("\n"));

            throw new ValidationConstraintException(message);
        }
    }
}
