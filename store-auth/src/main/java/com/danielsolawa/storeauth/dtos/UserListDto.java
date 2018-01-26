package com.danielsolawa.storeauth.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserListDto {

    List<UserDto> users;
}
