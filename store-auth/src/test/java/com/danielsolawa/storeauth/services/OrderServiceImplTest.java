package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.domain.Inventory;
import com.danielsolawa.storeauth.domain.Order;
import com.danielsolawa.storeauth.domain.Product;
import com.danielsolawa.storeauth.domain.User;
import com.danielsolawa.storeauth.dtos.OrderDto;
import com.danielsolawa.storeauth.mappers.OrderMapper;
import com.danielsolawa.storeauth.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

public class OrderServiceImplTest {

    OrderService orderService;


    @Mock
    UserRepository userRepository;

    OrderMapper orderMapper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        orderMapper = OrderMapper.INSTANCE;
        orderService = new OrderServiceImpl(userRepository, orderMapper);

    }

    @Test
    public void createNewOrder() {
        User user = getUser();
        User userWithOrders = getUserWithOrders();

        given(userRepository.findOne(anyLong())).willReturn(user);
        given(userRepository.save(any(User.class))).willReturn(userWithOrders);

        OrderDto orderDto = orderService.createNewOrder(1L, new OrderDto());

        assertThat(orderDto.getProducts(), hasSize(1));
        assertThat(orderDto.getId(), equalTo(1L));


        then(userRepository).should().findOne(anyLong());
        then(userRepository).should().save(any(User.class));




    }



    @Test
    public void updateOrder() {
        User user = getUserWithOrders();

        given(userRepository.findOne(anyLong())).willReturn(user);
        given(userRepository.save(any(User.class))).willReturn(user);

        OrderDto orderDto = orderService.updateOrder(
                1L, 1L, orderMapper.orderToOrderDto(user.getOrders().get(0)));

        assertThat(orderDto.getId(), equalTo(1L));

        then(userRepository).should().findOne(anyLong());
        then(userRepository).should().save(any(User.class));

    }

    @Test
    public void getOrderList() {
        User user = getUserWithOrders();

        given(userRepository.findOne(anyLong())).willReturn(user);

        List<OrderDto> orders = orderService.getOrderList(1L);

        assertThat(orders, hasSize(1));
        assertThat(orders.get(0).getId(), equalTo(1L));

        then(userRepository).should().findOne(anyLong());


    }

    @Test
    public void getOrderByIdHappyPath() {
        User user = getUserWithOrders();

        given(userRepository.findOne(anyLong())).willReturn(user);

        OrderDto orderDto = orderService.getOrderById(1L, 1L);

        assertNotNull(orderDto);
        assertThat(orderDto.getId(), equalTo(1L));

        then(userRepository).should().findOne(anyLong());

    }

    @Test(expected = RuntimeException.class)
    public void getOrderByIdFailure() {

        given(userRepository.findOne(anyLong())).willThrow(new RuntimeException());

        OrderDto orderDto = orderService.getOrderById(1L, 1L);

        assertNull(orderDto);

        then(userRepository).should().findOne(anyLong());

    }

    @Test
    public void deleteOrderById() {
        User user = getUserWithOrders();

        given(userRepository.findOne(anyLong())).willReturn(user);

        orderService.deleteOrderById(1L, 1L);

        then(userRepository).should().findOne(anyLong());
        then(userRepository).should().save(any(User.class));

    }


    private User getUserWithOrders() {
        User userWithOrders = new User();
        userWithOrders.setId(1L);

        Order order = new Order();
        order.setId(1L);
        order.addProduct(new Product());

        userWithOrders.setOrders(Arrays.asList(order));


        return userWithOrders;
    }


    private User getUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("Wendy");
        user.setPassword("password");
        return user;
    }
}