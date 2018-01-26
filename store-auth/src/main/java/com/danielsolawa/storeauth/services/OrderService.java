package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.dtos.OrderDto;

import java.util.List;

public interface OrderService {


    OrderDto createNewOrder(Long userId, OrderDto orderDto);
    OrderDto updateOrder(Long userId, Long orderId, OrderDto orderDto);
    List<OrderDto> getOrderList(Long userId);
    OrderDto getOrderById(Long userId, Long orderId);
    void deleteOrderById(Long userId, Long orderId);

}
