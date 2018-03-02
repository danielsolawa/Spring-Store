package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.dtos.ContactDto;

public interface ContactService {

    ContactDto createToOwner(ContactDto contactDto);
    ContactDto createToCustomer(ContactDto contactDto);
    ContactDto findByUserId(Long id);
    ContactDto findByUserUsername(String username);
    void deleteById(Long id);

}
