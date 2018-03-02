package com.danielsolawa.storeauth.controllers;

import com.danielsolawa.storeauth.dtos.ContactDto;
import com.danielsolawa.storeauth.dtos.ContactListDto;
import com.danielsolawa.storeauth.services.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ContactController.BASE_URL)
public class ContactController {

    public static final String BASE_URL = "/api/v1/contact";

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/to-owner")
    @ResponseStatus(HttpStatus.OK)
    ContactDto createToOwner(@RequestBody ContactDto contactDto){
        return contactService.createToOwner(contactDto);
    }


    @PostMapping("/to-customer")
    @ResponseStatus(HttpStatus.OK)
    ContactDto createToCustomer(@RequestBody ContactDto contactDto){
        return contactService.createToCustomer(contactDto);
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
}
