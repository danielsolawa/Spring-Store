package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.dtos.AddressDto;

public interface AddressService {

    AddressDto createNewAddress(Long userId, AddressDto addressDto);
    AddressDto updateAddress(Long userId, AddressDto addressDto);

}
