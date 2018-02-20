package com.danielsolawa.storeauth.controllers;


import com.danielsolawa.storeauth.services.ActivateAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ActivateAccountController.BASE_URL)
public class ActivateAccountController {

    public static final String BASE_URL =  "/api/v1/users";

    private final ActivateAccountService activateAccountService;

    public ActivateAccountController(ActivateAccountService activateAccountService) {
        this.activateAccountService = activateAccountService;
    }

    @GetMapping("/{username}/activate/{token}")
    @ResponseStatus(HttpStatus.OK)
    public void activateAccount(@PathVariable String username, @PathVariable String token){
        activateAccountService.activateAccount(username, token);

    }
    @GetMapping("/{username}/activate")
    @ResponseStatus(HttpStatus.OK)
    public void createNewToken(@PathVariable String username){
        activateAccountService.createNewToken(username);

    }
}
