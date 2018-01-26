package com.danielsolawa.storeauth.controllers;


import com.danielsolawa.storeauth.dtos.OrderDto;
import com.danielsolawa.storeauth.dtos.OrderListDto;
import com.danielsolawa.storeauth.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(OrderController.BASE_URL)
public class OrderController {

    public static final String BASE_URL = "/api/v1/users";

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/{userId}/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createNewOrder(@PathVariable Long userId,@RequestBody  OrderDto orderDto){
        return orderService.createNewOrder(userId, orderDto);
    }

    @PutMapping("/{userId}/orders/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto updateOrder(@PathVariable Long userId, @PathVariable Long orderId,@RequestBody  OrderDto orderDto){
        return orderService.updateOrder(userId, orderId, orderDto);
    }


    public OrderListDto getOrderList(){
        return null;
    }


    public OrderDto getOrderById(){
        return null;
    }

    public void deleteOrderById(){

    }


}
