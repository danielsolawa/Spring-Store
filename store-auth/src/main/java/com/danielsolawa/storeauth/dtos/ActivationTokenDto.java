package com.danielsolawa.storeauth.dtos;

import com.danielsolawa.storeauth.domain.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ActivationTokenDto {

    private Long id;
    private String token;
    private LocalDateTime expireDate;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;
}
