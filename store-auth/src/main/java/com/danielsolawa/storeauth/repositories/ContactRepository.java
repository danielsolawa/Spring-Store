package com.danielsolawa.storeauth.repositories;

import com.danielsolawa.storeauth.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    Contact findByUsersUsername(String username);
    Contact findByUsersId(Long id);
}
