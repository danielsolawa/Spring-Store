package com.danielsolawa.storeauth.dtos;


import com.danielsolawa.storeauth.domain.Inventory;
import com.danielsolawa.storeauth.domain.Order;
import com.danielsolawa.storeauth.domain.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {

    private Long id;
    @Email(message = "Please enter a valid email address.")
    @Size(min = 8, max = 30, message = "Please enter an email between 8 and 30 characters.")
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 8, max = 30, message = "Please enter a password between 8 and 30 characters.")
    private String password;

    private Role role;
    private List<Order> orders = new ArrayList<>();
    private Inventory inventory;
}
