package com.danielsolawa.storeauth.repositories;

import com.danielsolawa.storeauth.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findByConversationId(String conversationId);
    List<Contact> findByUsersUsername(String username);
    List<Contact> findByUsersId(Long id);
}
