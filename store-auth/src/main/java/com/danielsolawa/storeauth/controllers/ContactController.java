package com.danielsolawa.storeauth.controllers;

import com.danielsolawa.storeauth.dtos.ContactDto;
import com.danielsolawa.storeauth.dtos.ContactListDto;
import com.danielsolawa.storeauth.services.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ContactController.BASE_URL)
public class ContactController {

    public static final String BASE_URL = "/api/v1/contacts";

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/to-owner/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    ContactDto createToOwner(@PathVariable Long userId, @RequestBody ContactDto contactDto){
        return contactService.createToOwner(userId, contactDto);
    }

    @PutMapping("/update-conversation-to-owner/users/{userId}/conversation/{conversationId}")
    @ResponseStatus(HttpStatus.OK)
    ContactDto updateToOwner(@PathVariable Long userId, @PathVariable String conversationId,
                             @RequestBody ContactDto contactDto){
        return contactService.updateConversationToOwner(userId,conversationId, contactDto);
    }

    @PutMapping("/update-conversation-to-customer/users/{userId}/conversation/{conversationId}")
    @ResponseStatus(HttpStatus.OK)
    ContactDto updateToCustomer(@PathVariable Long userId, @PathVariable String conversationId,
                             @RequestBody ContactDto contactDto){
        return contactService.updateConversationToCustomer(userId,conversationId, contactDto);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ContactListDto getAll(){
        return new ContactListDto(contactService.getAll());
    }

    @GetMapping("/find-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    ContactListDto findByUserId(@PathVariable Long id){
        return new ContactListDto(contactService.findByUserId(id));
    }

    @GetMapping("/find-by-username/{username}")
    @ResponseStatus(HttpStatus.OK)
    ContactListDto findByUserUsername(@PathVariable String username){
        return new ContactListDto(contactService.findByUserUsername(username));
    }

    @GetMapping("/find-by-conversation/{conversationId}")
    @ResponseStatus(HttpStatus.OK)
    ContactListDto findByConversationId(@PathVariable String conversationId){
        return new ContactListDto(contactService.findByConversationId(conversationId));
    }
}
