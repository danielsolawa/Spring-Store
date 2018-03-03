package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.dtos.ContactDto;

import java.util.List;

public interface ContactService {

    ContactDto createToOwner(Long userId, ContactDto contactDto);
    ContactDto updateConversationToOwner(Long userId, String conversationId, ContactDto contactDto);
    ContactDto updateConversationToCustomer(Long userId, String conversationId, ContactDto contactDto);
    List<ContactDto> getAll();
    List<ContactDto> findByConversationId(String conversationId);
    List<ContactDto> findByUserId(Long id);
    List<ContactDto> findByUserUsername(String username);
    void deleteById(Long id);

}
