package com.danielsolawa.storeauth.controllers;

import com.danielsolawa.storeauth.dtos.ContactDto;
import com.danielsolawa.storeauth.dtos.ContactListDto;
import com.danielsolawa.storeauth.services.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ContactController.BASE_URL)
public class ContactController {

    public static final String BASE_URL = "/api/v1/contacts";

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    ContactDto createToOwner(@RequestBody ContactDto contactDto){
        return contactService.createToOwner(contactDto);
    }

    @PostMapping("/{conversationId}")
    @ResponseStatus(HttpStatus.OK)
    ContactDto updateToOwner(@PathVariable String conversationId,
                             @RequestBody ContactDto contactDto){
        return contactService.updateConversationToOwner(conversationId, contactDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{conversationId}")
    @ResponseStatus(HttpStatus.OK)
    ContactDto updateToCustomer(@PathVariable String conversationId,
                             @RequestBody ContactDto contactDto){
        return contactService.updateConversationToCustomer(conversationId, contactDto);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ContactListDto getAll(){
        return new ContactListDto(contactService.getAll());
    }

    @GetMapping("/{conversationId}")
    @ResponseStatus(HttpStatus.OK)
    ContactListDto findByConversationId(@PathVariable String conversationId){
        return new ContactListDto(contactService.findByConversationId(conversationId));
    }


}
