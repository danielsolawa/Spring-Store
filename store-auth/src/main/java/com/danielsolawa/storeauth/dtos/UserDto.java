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
    @Email(message = "{Email.user.username}")
    @Size(min = 8, max = 30, message = "{Size.user.username}")
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 8, max = 30, message = "{Size.user.password }")
    private String password;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private Role role;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Order> orders = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Inventory inventory;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ActivationTokenDto activationToken;
}
