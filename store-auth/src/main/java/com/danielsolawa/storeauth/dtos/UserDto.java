package com.danielsolawa.storeauth.dtos;


import com.danielsolawa.storeauth.domain.Inventory;
import com.danielsolawa.storeauth.domain.Order;
import com.danielsolawa.storeauth.domain.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private Role role;
    private List<Order> orders = new ArrayList<>();
    private Inventory inventory;
}
