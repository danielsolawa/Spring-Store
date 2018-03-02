package com.danielsolawa.storeauth.mappers;

import com.danielsolawa.storeauth.domain.Contact;
import com.danielsolawa.storeauth.dtos.ContactDto;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class ContactMapperTest {

    ContactMapper contactMapper;

    @Before
    public void setUp() throws Exception {
        contactMapper = ContactMapper.INSTANCE;
    }

    @Test
    public void contactDtoToContact() {
        ContactDto contactDto = new ContactDto();
        contactDto.setId(1L);

        Contact contact = contactMapper.contactDtoToContact(contactDto);

        assertThat(contact.getId(), equalTo(1L));
    }


    @Test
    public void contactDtoToContactFailure() {
        Contact contact = contactMapper.contactDtoToContact(null);

        assertThat(contact, nullValue());
    }



    @Test
    public void contactToContactDto() {
        Contact contact = new Contact();
        contact.setId(2L);

        ContactDto contactDto = contactMapper.contactToContactDto(contact);

        assertThat(contactDto.getId(), equalTo(2L));
    }


    @Test
    public void contactToContactDtoFailure() {


        ContactDto contactDto = contactMapper.contactToContactDto(null);

        assertThat(contactDto, nullValue());
    }
}