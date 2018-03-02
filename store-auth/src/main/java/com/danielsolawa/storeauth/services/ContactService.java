package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.dtos.ContactDto;

import java.util.List;

public interface ContactService {

    ContactDto createToOwner(ContactDto contactDto);
    ContactDto createToCustomer(ContactDto contactDto);
    List<ContactDto> getAll();
    List<ContactDto> findByUserId(Long id);
    List<ContactDto> findByUserUsername(String username);
    void deleteById(Long id);

}
