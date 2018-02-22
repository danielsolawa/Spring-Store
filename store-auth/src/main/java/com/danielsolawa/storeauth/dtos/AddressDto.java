package com.danielsolawa.storeauth.dtos;

import com.danielsolawa.storeauth.domain.User;
import lombok.Data;

@Data
public class AddressDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String street;
    private String country;
    private String zipCode;
    private String phoneNumber;
    private User user;
}
