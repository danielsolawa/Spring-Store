package com.danielsolawa.storeauth.controllers;

import com.danielsolawa.storeauth.dtos.AddressDto;
import com.danielsolawa.storeauth.services.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AddressController.BASE_URL)
public class AddressController {

    public static final String BASE_URL = "/api/v1/users";

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }


    @GetMapping("/{userId}/address")
    @ResponseStatus(HttpStatus.OK)
    public AddressDto getAddressDto(@PathVariable Long userId){
        return addressService.getAddress(userId);
    }

    @PostMapping("/{userId}/address")
    @ResponseStatus(HttpStatus.OK)
    public AddressDto createNewAddress(@PathVariable Long userId, @RequestBody AddressDto addressDto){
        return addressService.createNewAddress(userId, addressDto);
    }

    @PutMapping("/{userId}/address")
    @ResponseStatus(HttpStatus.OK)
    public AddressDto updateAddress(@PathVariable Long userId, @RequestBody AddressDto addressDto){
        return addressService.updateAddress(userId, addressDto);
    }
}
