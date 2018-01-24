package com.danielsolawa.storeauth.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PrincipalController.BASE_URL)
public class PrincipalController {

    public static final String BASE_URL =  "/api/v1/principal";

    @GetMapping
    public String index(){
        return "Hello world";
    }
}
