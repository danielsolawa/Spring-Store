package com.danielsolawa.storeauth.mappers;

import com.danielsolawa.storeauth.domain.Order;
import com.danielsolawa.storeauth.domain.Product;
import com.danielsolawa.storeauth.domain.User;
import com.danielsolawa.storeauth.dtos.OrderDto;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

public class OrderMapperTest {

    OrderMapper orderMapper;

    @Before
    public void setUp() throws Exception {

        orderMapper = OrderMapper.INSTANCE;
    }

    @Test
    public void orderDtoToOrder() {
        LocalDateTime time = LocalDateTime.now();

        OrderDto orderDto = new OrderDto();
        orderDto.setProducts(Arrays.asList(new Product(), new Product()));
        orderDto.setOrderDate(time);
        orderDto.setUser(new User());

        Order order = orderMapper.orderDtoToOrder(orderDto);

        assertThat(order.getProducts(), hasSize(2));
        assertThat(orderDto.getOrderDate(), equalTo(order.getOrderDate()));
        assertNotNull(order.getUser());


    }

    @Test
    public void orderToOrderDto() {
        LocalDateTime time = LocalDateTime.now();

        Order order = new Order();
        order.setProducts(Arrays.asList(new Product(), new Product(), new Product()));
        order.setOrderDate(time);
        order.setUser(new User());

        OrderDto orderDto = orderMapper.orderToOrderDto(order);

        assertThat(orderDto.getProducts(), hasSize(3));
        assertThat(order.getOrderDate(), equalTo(orderDto.getOrderDate()));
        assertNotNull(orderDto.getUser());

    }
}