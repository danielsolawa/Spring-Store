package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.domain.Address;
import com.danielsolawa.storeauth.domain.User;
import com.danielsolawa.storeauth.dtos.AddressDto;
import com.danielsolawa.storeauth.exceptions.ResourceNotFoundException;
import com.danielsolawa.storeauth.mappers.AddressMapper;
import com.danielsolawa.storeauth.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    private final UserRepository userRepository;
    private final AddressMapper addressMapper;

    public AddressServiceImpl(UserRepository userRepository, AddressMapper addressMapper) {
        this.userRepository = userRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public AddressDto getAddress(Long userId) {
        User user = fetchUser(userId);

        if(user.getAddress() == null){
            throw new ResourceNotFoundException("Address not found");
        }

        return addressMapper.addressToAddressDto(user.getAddress());
    }

    @Override
    public AddressDto createNewAddress(Long userId, AddressDto addressDto) {
        User user = fetchUser(userId);


        return saveAddressDto(addressDto, user);
    }


    @Override
    public AddressDto updateAddress(Long userId, AddressDto addressDto) {
        User user = fetchUser(userId);

       return saveAddressDto(prepareForUpdate(user, addressDto), user);
    }


    private User fetchUser(Long userId) {
        User user = userRepository.findOne(userId);

        if(user == null){
            throw new ResourceNotFoundException("User not found");
        }

        return user;
    }

    private AddressDto prepareForUpdate(User user, AddressDto addressDto) {
        Address address = user.getAddress();

        if(addressDto.getFirstName() != null){
            address.setFirstName(addressDto.getFirstName());
        }

        if(addressDto.getLastName() != null){
            address.setLastName(addressDto.getLastName());
        }

        if(addressDto.getCountry() != null){
            address.setCountry(addressDto.getCountry());
        }

        if(addressDto.getStreet() != null){
            address.setStreet(addressDto.getStreet());
        }

        if(addressDto.getCity() != null){
            address.setCity(addressDto.getCity());
        }

        if(addressDto.getZipCode() != null){
            address.setZipCode(addressDto.getZipCode());
        }

        if(addressDto.getPhoneNumber() != null){
            address.setPhoneNumber(addressDto.getPhoneNumber());
        }

        return addressMapper.addressToAddressDto(address);
    }

    private AddressDto saveAddressDto(AddressDto addressDto, User user) {
        addressDto.setUser(user);
        user.setAddress(addressMapper.addressDtoToAddress(addressDto));

        User savedUser = userRepository.save(user);

        return addressMapper.addressToAddressDto(savedUser.getAddress());
    }
}
