package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.domain.Contact;
import com.danielsolawa.storeauth.dtos.ContactDto;
import com.danielsolawa.storeauth.mappers.ContactMapper;
import com.danielsolawa.storeauth.repositories.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    public ContactServiceImpl(ContactRepository contactRepository, ContactMapper contactMapper) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }

    @Override
    public ContactDto createToOwner(ContactDto contactDto) {
        return saveContact(contactDto);
    }



    @Override
    public ContactDto createToCustomer(ContactDto contactDto) {
        return saveContact(contactDto);
    }

    @Override
    public ContactDto findByUserId(Long id) {
        return contactMapper.contactToContactDto(contactRepository.findByUsersId(id));
    }

    @Override
    public ContactDto findByUserUsername(String username) {
        return contactMapper.contactToContactDto(contactRepository.findByUsersUsername(username));
    }

    @Override
    public void deleteById(Long id) {
        contactRepository.delete(id);
    }


    private ContactDto saveContact(ContactDto contactDto) {
        Contact contact = contactMapper.contactDtoToContact(contactDto);

        Contact savedContact = contactRepository.save(contact);

        return contactMapper.contactToContactDto(savedContact);
    }
}
