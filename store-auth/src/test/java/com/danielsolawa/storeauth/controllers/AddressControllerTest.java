package com.danielsolawa.storeauth.controllers;

import com.danielsolawa.storeauth.dtos.AddressDto;
import com.danielsolawa.storeauth.services.AddressService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AddressControllerTest extends AbstractControllerTest{

    MockMvc mockMvc;

    AddressController addressController;

    @Mock
    AddressService addressService;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        addressController = new AddressController(addressService);
        mockMvc = MockMvcBuilders.standaloneSetup(addressController).build();

    }


    @Test
    public void getAddressDto() throws Exception {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(1L);

        given(addressService.getAddress(anyLong())).willReturn(addressDto);

        mockMvc.perform(get(AddressController.BASE_URL + "/1/address")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));

        then(addressService).should().getAddress(anyLong());
    }

    @Test
    public void createNewAddress() throws Exception {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(1L);

        given(addressService.createNewAddress(anyLong(), Matchers.any())).willReturn(addressDto);

        mockMvc.perform(post(AddressController.BASE_URL + "/1/address")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJson(addressDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));


        then(addressService).should().createNewAddress(anyLong(), Matchers.any());


    }

    @Test
    public void updateAddress() throws Exception {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(1L);

        given(addressService.updateAddress(anyLong(), Matchers.any())).willReturn(addressDto);

        mockMvc.perform(put(AddressController.BASE_URL + "/1/address")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJson(addressDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));


        then(addressService).should().updateAddress(anyLong(), Matchers.any());


    }
}