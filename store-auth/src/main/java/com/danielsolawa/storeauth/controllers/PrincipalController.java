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
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();


        return  getUserInfo(userDetails);
    }


    private UserInfo getUserInfo(UserDetails userDetails){
        return UserInfo.builder()
                        .username(userDetails.getUsername())
                        .authority(userDetails.getAuthorities()
                                            .stream()
                                            .findFirst()
                                            .get()
                                            .getAuthority()).build();
    }
}
