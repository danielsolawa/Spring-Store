package com.danielsolawa.storeauth.mappers;

import com.danielsolawa.storeauth.domain.Address;
import com.danielsolawa.storeauth.dtos.AddressDto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddressMapperTest {

    AddressMapper addressMapper;

    @Before
    public void setUp() throws Exception {
        addressMapper = AddressMapper.INSTANCE;
    }


    @Test
    public void addressToAddressDtoSuccess() {
        Address address = new Address();
        address.setId(1L);
        address.setFirstName("Daniel");

        AddressDto addressDto = addressMapper.addressToAddressDto(address);

        assertEquals(address.getId(), addressDto.getId());
        assertEquals(address.getFirstName(), addressDto.getFirstName());
    }


    @Test
    public void addressToAddressDtoFailure() {
        AddressDto addressDto = addressMapper.addressToAddressDto(null);

        assertNull(addressDto);

    }

    @Test
    public void addressDtoToAddressSuccess() {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(1L);
        addressDto.setFirstName("Daniel");

        Address address = addressMapper.addressDtoToAddress(addressDto);

        assertEquals(addressDto.getId(), address.getId());
        assertEquals(addressDto.getFirstName(), address.getFirstName());
    }


    @Test
    public void addressDtoToAddressFailure() {
        Address address = addressMapper.addressDtoToAddress(null);

        assertNull(address);
    }
}