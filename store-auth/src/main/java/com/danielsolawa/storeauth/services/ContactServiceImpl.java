package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.domain.Contact;
import com.danielsolawa.storeauth.domain.MessageFrom;
import com.danielsolawa.storeauth.domain.User;
import com.danielsolawa.storeauth.dtos.ContactDto;
import com.danielsolawa.storeauth.exceptions.ResourceNotFoundException;
import com.danielsolawa.storeauth.mappers.ContactMapper;
import com.danielsolawa.storeauth.repositories.ContactRepository;
import com.danielsolawa.storeauth.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ContactServiceImpl implements ContactService {

    private final UserRepository userRepository;
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;
    private final ContactEmailService contactEmailService;


    public ContactServiceImpl( UserRepository userRepository,
                              ContactRepository contactRepository, ContactMapper contactMapper,
                              ContactEmailService contactEmailService) {
        this.userRepository = userRepository;
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
        this.contactEmailService = contactEmailService;
    }

    @Override
    public ContactDto createToOwner(ContactDto contactDto) {

        return saveContact(contactDto, contactDto.getUserId());
    }


    @Override
    public ContactDto updateConversationToOwner(String conversationId, ContactDto contactDto) {
        return updateContact(contactDto.getUserId(), conversationId, contactDto, true);
    }

    @Override
    public ContactDto updateConversationToCustomer(String conversationId, ContactDto contactDto) {
        return updateContact(contactDto.getUserId(), conversationId, contactDto, false);
    }


    @Override
    public List<ContactDto> getAll() {
        return contactRepository.findAll()
                .stream()
                .map(contactMapper::contactToContactDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ContactDto> findByConversationId(String conversationId) {
        return contactRepository.findByConversationId(conversationId)
                .stream()
                .map(contactMapper::contactToContactDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ContactDto> findByUserId(Long id) {
        return contactRepository.findByUserIdOrderByDateDesc(id)
                .stream()
                .map(contactMapper::contactToContactDto)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteById(Long id) {
        contactRepository.delete(id);
    }


    private ContactDto saveContact(ContactDto contactDto, Long userId) {
        User user = userRepository.findOne(userId);

        if(user == null){
            throw new ResourceNotFoundException("User not found");
        }

        String conversationId =  UUID.randomUUID().toString().replaceAll("-", "");

        Contact contact = contactMapper.contactDtoToContact(contactDto);
        contact.addUser(user);
        contact.setMessageFrom(MessageFrom.USER);
        contact.setDate(LocalDateTime.now());
        contact.setUserId(user.getId());
        contact.setConversationId(conversationId);

        Contact savedContact = contactRepository.save(contact);

        try {
            contactEmailService.sendEmailToOwner(savedContact);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return contactMapper.contactToContactDto(savedContact);
    }

    private ContactDto updateContact(Long userId, String conversationId, ContactDto contactDto, boolean toOwner) {
        User user = userRepository.findOne(userId);

        if(user == null){
            throw new ResourceNotFoundException("User not found.");
        }

        if(!conversationExists(conversationId)){
            throw new ResourceNotFoundException("Conversation doesn't exist.");
        }

        Contact contactToSave = contactMapper.contactDtoToContact(contactDto);
        MessageFrom messageFrom = toOwner ? MessageFrom.USER : MessageFrom.ADMIN;

        contactToSave.addUser(user);
        contactToSave.setSubject("RE: " + contactToSave.getSubject());
        contactToSave.setMessageFrom(messageFrom);
        contactToSave.setConversationId(conversationId);
        contactToSave.setUserId(user.getId());
        contactToSave.setDate(LocalDateTime.now());

        Contact savedContact = contactRepository.save(contactToSave);

        try {
            if(toOwner){
                contactEmailService.sendEmailToOwner(savedContact);
            }else{
                contactEmailService.sendEmailToCustomer(savedContact);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return contactMapper.contactToContactDto(savedContact);
    }


    private boolean conversationExists(String conversationId) {
        return findByConversationId(conversationId).size() > 0;
    }
}
