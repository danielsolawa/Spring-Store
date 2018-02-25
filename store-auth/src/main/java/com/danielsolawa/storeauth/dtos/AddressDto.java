package com.danielsolawa.storeauth.dtos;

import com.danielsolawa.storeauth.domain.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddressDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String country;
    private String zipCode;
    private String phoneNumber;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;
}
