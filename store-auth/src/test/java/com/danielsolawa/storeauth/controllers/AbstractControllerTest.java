package com.danielsolawa.storeauth.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractControllerTest {

    protected static String asJson(Object o){
        try {
            return new  ObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Can't map to json");
        }
    }
}
