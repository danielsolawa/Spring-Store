package com.danielsolawa.storeauth.controllers;

import com.danielsolawa.storeauth.dtos.OrderDto;
import com.danielsolawa.storeauth.services.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class OrderControllerTest extends AbstractControllerTest {

    @InjectMocks
    OrderController orderController;

    @Mock
    OrderService orderService;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc= MockMvcBuilders.standaloneSetup(orderController).build();

    }

    @Test
    public void createNewOrder() throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(1L);

        given(orderService.createNewOrder(anyLong(), any(OrderDto.class))).willReturn(orderDto);

        mockMvc.perform(post(OrderController.BASE_URL + "/1/orders")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(orderDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(1)));

        then(orderService).should().createNewOrder(anyLong(), any(OrderDto.class));


    }

    @Test
    public void updateOrder() throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(1L);

        given(orderService.updateOrder(anyLong(),anyLong(), any(OrderDto.class))).willReturn(orderDto);

        mockMvc.perform(put(OrderController.BASE_URL + "/1/orders/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(orderDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));

        then(orderService).should().updateOrder(anyLong(),anyLong(), any(OrderDto.class));

    }

    @Test
    public void getOrderList() {
    }

    @Test
    public void getOrderById() {
    }

    @Test
    public void deleteOrderById() {
    }
}