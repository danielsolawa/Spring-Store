package com.danielsolawa.storeauth.mappers;

import com.danielsolawa.storeauth.domain.User;
import com.danielsolawa.storeauth.dtos.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userDtoToUser(UserDto userDto);
    UserDto userToUserDto (User user);
}
