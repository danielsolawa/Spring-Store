package com.danielsolawa.storeauth.dtos;

import com.danielsolawa.storeauth.domain.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ContactDto {

    private Long id;
    private List<User> users = new ArrayList<>();
    private String conversationId;
    private String subject;
    private String content;
    private LocalDateTime date;
}
