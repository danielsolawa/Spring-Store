package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.dtos.ContactDto;

import java.util.List;

public interface ContactService {

    ContactDto createToOwner(ContactDto contactDto);
    ContactDto updateConversationToOwner(String conversationId, ContactDto contactDto);
    ContactDto updateConversationToCustomer(String conversationId, ContactDto contactDto);
    List<ContactDto> getAll();
    List<ContactDto> findByConversationId(String conversationId);
    void deleteById(Long id);

}
