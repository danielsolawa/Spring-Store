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
    public OrderDto createNewOrder(@PathVariable Long userId, @RequestBody OrderDto orderDto){
        return orderService.createNewOrder(userId, orderDto);
    }

    @PutMapping("/{userId}/orders/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto updateOrder(@PathVariable Long userId, @PathVariable Long orderId,@RequestBody  OrderDto orderDto){
        return orderService.updateOrder(userId, orderId, orderDto);
    }

    @GetMapping("/{userId}/orders")
    @ResponseStatus(HttpStatus.OK)
    public OrderListDto getOrderList(@PathVariable Long userId){
        return new OrderListDto(orderService.getOrderList(userId));
    }

    @GetMapping("/{userId}/orders/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto getOrderById(@PathVariable Long userId, @PathVariable Long orderId){
        return orderService.getOrderById(userId, orderId);
    }

    @DeleteMapping("/{userId}/orders/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrderById(@PathVariable Long userId, @PathVariable Long orderId){
        orderService.deleteOrderById(userId, orderId);
    }


}
