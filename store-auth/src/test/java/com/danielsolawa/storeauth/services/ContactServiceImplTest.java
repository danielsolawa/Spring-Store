package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.domain.Contact;
import com.danielsolawa.storeauth.dtos.ContactDto;
import com.danielsolawa.storeauth.mappers.ContactMapper;
import com.danielsolawa.storeauth.repositories.ContactRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.Matchers.equalTo;
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

    ContactMapper contactMapper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        contactMapper = ContactMapper.INSTANCE;
        contactService = new ContactServiceImpl(contactRepository, contactMapper);
    }

    @Test
    public void createToOwner() {
        Contact contact = new Contact();
        contact.setId(1L);
        contact.setContent("message");

        given(contactRepository.save(any(Contact.class))).willReturn(contact);

        ContactDto contactDto = contactService.createToOwner(new ContactDto());

        assertNotNull(contactDto);
        assertThat(contactDto.getId(), equalTo(1L));
        assertThat(contactDto.getContent(), equalTo("message"));

        then(contactRepository).should().save(any(Contact.class));


    }

    @Test
    public void createToCustomer() {
        Contact contact = new Contact();
        contact.setId(2L);
        contact.setContent("message");

        given(contactRepository.save(any(Contact.class))).willReturn(contact);

        ContactDto contactDto = contactService.createToCustomer(new ContactDto());

        assertNotNull(contactDto);
        assertThat(contactDto.getId(), equalTo(2L));
        assertThat(contactDto.getContent(), equalTo("message"));

        then(contactRepository).should().save(any(Contact.class));

    }

    @Test
    public void findByUserId() {
        Contact contact = new Contact();
        contact.setId(1L);

        given(contactRepository.findByUsersId(anyLong())).willReturn(contact);

        ContactDto contactDto = contactService.findByUserId(1L);

        assertNotNull(contactDto);
        assertThat(contactDto.getId(), equalTo(1L));

        then(contactRepository).should().findByUsersId(anyLong());
    }

    @Test
    public void findByUserUsername() {
        Contact contact = new Contact();
        contact.setId(1L);

        given(contactRepository.findByUsersUsername(anyString())).willReturn(contact);

        ContactDto contactDto = contactService.findByUserUsername("username");

        assertNotNull(contactDto);
        assertThat(contactDto.getId(), equalTo(1L));

        then(contactRepository).should().findByUsersUsername(anyString());

    }

    @Test
    public void deleteById() {

        contactRepository.delete(1L);

        then(contactRepository).should().delete(anyLong());
    }
}