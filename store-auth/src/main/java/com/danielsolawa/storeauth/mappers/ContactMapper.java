package com.danielsolawa.storeauth.mappers;

import com.danielsolawa.storeauth.domain.Contact;
import com.danielsolawa.storeauth.dtos.ContactDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    Contact contactDtoToContact(ContactDto contactDto);
    ContactDto contactToContactDto(Contact contact);
}
