package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.domain.Contact;
import com.danielsolawa.storeauth.domain.User;
import com.danielsolawa.storeauth.dtos.ContactDto;
import com.danielsolawa.storeauth.mappers.ContactMapper;
import com.danielsolawa.storeauth.repositories.ContactRepository;
import com.danielsolawa.storeauth.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;

public class ContactServiceImplTest {

    ContactService contactService;

    @Mock
    ContactRepository contactRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    ContactEmailService contactEmailService;

    ContactMapper contactMapper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        contactMapper = ContactMapper.INSTANCE;
        contactService = new ContactServiceImpl(userRepository, contactRepository,
                contactMapper, contactEmailService);
    }

    @Test
    public void createToOwner() {
        Contact contact = new Contact();
        contact.setId(1L);
        contact.setContent("message");

        given(userRepository.findOne(anyLong())).willReturn(new User());
        given(contactRepository.save(any(Contact.class))).willReturn(contact);

        ContactDto contactDto = contactService.createToOwner(new ContactDto());

        assertNotNull(contactDto);
        assertThat(contactDto.getId(), equalTo(1L));
        assertThat(contactDto.getContent(), equalTo("message"));

        then(contactRepository).should().save(any(Contact.class));


    }

    @Test
    public void updateConversationToOwner() {
        Contact contact = new Contact();
        contact.setId(1L);

        List<Contact> contacts = Arrays.asList(new Contact(), new Contact());

        given(userRepository.findOne(anyLong())).willReturn(new User());
        given(contactRepository.findByConversationId(anyString())).willReturn(contacts);
        given(contactRepository.save(any(Contact.class))).willReturn(contact);

        ContactDto contactDto = contactService.updateConversationToOwner("id", new ContactDto());

        assertNotNull(contactDto);
        assertThat(contactDto.getId(), equalTo(1L));

        then(userRepository).should().findOne(anyLong());
        then(contactRepository).should().findByConversationId(anyString());
        then(contactRepository).should().save(any(Contact.class));


    }


    @Test
    public void updateConversationToCustomer() {
        Contact contact = new Contact();
        contact.setId(2L);

        List<Contact> contacts = Arrays.asList(new Contact(), new Contact());

        given(userRepository.findOne(anyLong())).willReturn(new User());
        given(contactRepository.findByConversationId(anyString())).willReturn(contacts);
        given(contactRepository.save(any(Contact.class))).willReturn(contact);

        ContactDto contactDto = contactService.updateConversationToCustomer( "conversationId",
                new ContactDto());

        assertNotNull(contactDto);
        assertThat(contactDto.getId(), equalTo(2L));

        then(userRepository).should().findOne(anyLong());
        then(contactRepository).should().findByConversationId(anyString());
        then(contactRepository).should().save(any(Contact.class));


    }

    @Test
    public void getAll() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact());
        contacts.add(new Contact());
        contacts.add(new Contact());

        given(contactRepository.findAll()).willReturn(contacts);

        List<ContactDto> contactDtos = contactService.getAll();

        assertThat(contactDtos, hasSize(3));

        then(contactRepository).should().findAll();
    }


    @Test
    public void findByConversationId() {
        List<Contact> contacts = Arrays.asList(new Contact(), new Contact(), new Contact());

        given(contactRepository.findByConversationId(anyString())).willReturn(contacts);

        List<ContactDto> contactDtos = contactService.findByConversationId("id");

        assertThat(contactDtos, hasSize(3));

        then(contactRepository).should().findByConversationId(anyString());
    }

    @Test
    public void findByUserId() {
        List<Contact> contacts = Arrays.asList(new Contact(), new Contact(), new Contact());

        given(contactRepository.findByUserIdOrderByDateDesc(anyLong())).willReturn(contacts);

        List<ContactDto> contactDtos = contactService.findByUserId(1L);

        assertThat(contactDtos, hasSize(3));

        then(contactRepository).should().findByUserIdOrderByDateDesc(anyLong());
    }

    @Test
    public void deleteById() {

        contactRepository.delete(1L);

        then(contactRepository).should().delete(anyLong());
    }
}