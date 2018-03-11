package com.danielsolawa.storeauth.controllers;


import com.danielsolawa.storeauth.dtos.UserDto;
import com.danielsolawa.storeauth.dtos.UserListDto;
import com.danielsolawa.storeauth.exceptions.ValidationConstraintException;
import com.danielsolawa.storeauth.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserListDto getUserListPagination(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                             @RequestParam(name = "size", required = false, defaultValue = "0") Integer size){
        if(page.equals(0) && size.equals(0)){
            return new UserListDto(userService.getUserList(), userService.getUserListSize());
        }

        return new UserListDto(userService.getUserList(page, size), userService.getUserListSize());
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


    @PreAuthorize("hasAuthority('ADMIN')")
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
