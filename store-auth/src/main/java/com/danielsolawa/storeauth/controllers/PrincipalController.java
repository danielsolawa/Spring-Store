package com.danielsolawa.storeauth.controllers;


import com.danielsolawa.storeauth.utils.UserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(PrincipalController.BASE_URL)
public class PrincipalController {

    public static final String BASE_URL =  "/api/v1/principal";


    @GetMapping
    public UserInfo authentication(Authentication authentication){
        UserInfo userInfo = (UserInfo) authentication.getPrincipal();


        return  userInfo;
    }



}
