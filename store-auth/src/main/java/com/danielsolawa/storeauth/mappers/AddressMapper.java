package com.danielsolawa.storeauth.mappers;

import com.danielsolawa.storeauth.domain.Address;
import com.danielsolawa.storeauth.dtos.AddressDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    Address addressDtoToAddress(AddressDto addressDto);
    AddressDto addressToAddressDto(Address address);
}
