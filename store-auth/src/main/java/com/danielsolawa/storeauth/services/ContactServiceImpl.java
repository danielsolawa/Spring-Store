package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.domain.Contact;
import com.danielsolawa.storeauth.dtos.ContactDto;
import com.danielsolawa.storeauth.mappers.ContactMapper;
import com.danielsolawa.storeauth.repositories.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;
    private final EmailService emailService;

    public ContactServiceImpl(ContactRepository contactRepository, ContactMapper contactMapper, EmailService emailService) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
        this.emailService = emailService;
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
    public List<ContactDto> findByUserId(Long id) {
        return contactRepository.findByUsersId(id)
                .stream()
                .map(contactMapper::contactToContactDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ContactDto> findByUserUsername(String username) {

        return contactRepository.findByUsersUsername(username)
                .stream()
                .map(contactMapper::contactToContactDto)
                .collect(Collectors.toList());
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
