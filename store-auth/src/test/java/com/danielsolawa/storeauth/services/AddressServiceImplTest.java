package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.domain.Address;
import com.danielsolawa.storeauth.domain.User;
import com.danielsolawa.storeauth.dtos.AddressDto;
import com.danielsolawa.storeauth.exceptions.ResourceNotFoundException;
import com.danielsolawa.storeauth.mappers.AddressMapper;
import com.danielsolawa.storeauth.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class AddressServiceImplTest {

    AddressService addressService;

    AddressMapper addressMapper;

    @Mock
    UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        addressMapper = AddressMapper.INSTANCE;
        addressService = new AddressServiceImpl(userRepository, addressMapper);
    }


    @Test
    public void getAddressHappyPath() {
        User user = new User();
        user.setId(1L);

        Address address = new Address();
        address.setId(1L);

        user.setAddress(address);

        given(userRepository.findOne(anyLong())).willReturn(user);

        AddressDto addressDto = addressService.getAddress(1L);

        assertThat(address.getId(), equalTo(addressDto.getId()));

        then(userRepository).should().findOne(anyLong());

    }


    @Test(expected = ResourceNotFoundException.class)
    public void getAddressFailure(){

        given(userRepository.findOne(anyLong())).willThrow(ResourceNotFoundException.class);

        AddressDto addressDto = addressService.getAddress(1L);

        assertNull(addressDto);

        then(userRepository).should().findOne(anyLong());
    }

    @Test
    public void createNewAddress() {
        User user = new User();
        user.setId(1L);

        Address address = new Address();
        address.setId(1L);

        user.setAddress(address);
        address.setUser(user);

        given(userRepository.findOne(anyLong())).willReturn(user);
        given(userRepository.save(any(User.class))).willReturn(user);

        AddressDto addressDto = addressService.createNewAddress(1L, addressMapper.addressToAddressDto(address));

        assertThat(addressDto.getId(), equalTo(address.getId()));


        then(userRepository).should().findOne(anyLong());
        then(userRepository).should().save(any(User.class));

    }

    @Test
    public void updateAddress() {
        User user = new User();
        user.setId(1L);

        Address address = new Address();
        address.setId(1L);
        address.setFirstName("Daniel");

        user.setAddress(address);
        address.setUser(user);

        AddressDto addressToUpdate = new AddressDto();
        addressToUpdate.setId(1L);
        addressToUpdate.setFirstName("John");

        given(userRepository.findOne(anyLong())).willReturn(user);
        given(userRepository.save(any(User.class))).willReturn(user);

        AddressDto addressDto = addressService.updateAddress(1L, addressToUpdate);

        assertNotEquals("Daniel", addressDto.getFirstName());

        then(userRepository).should().findOne(anyLong());
        then(userRepository).should().save(any(User.class));

    }
}