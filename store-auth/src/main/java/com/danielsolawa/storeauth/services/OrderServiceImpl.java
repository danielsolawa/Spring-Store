package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.domain.Order;
import com.danielsolawa.storeauth.domain.Product;
import com.danielsolawa.storeauth.domain.User;
import com.danielsolawa.storeauth.dtos.EmailDto;
import com.danielsolawa.storeauth.dtos.OrderDto;
import com.danielsolawa.storeauth.exceptions.ResourceNotFoundException;
import com.danielsolawa.storeauth.mappers.OrderMapper;
import com.danielsolawa.storeauth.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@Slf4j
@Service
public class OrderServiceImpl implements OrderService {


    private final UserRepository userRepository;
    private final OrderMapper orderMapper;
    private final EmailService emailService;
    private final String storeEmail;

    public OrderServiceImpl(UserRepository userRepository, OrderMapper orderMapper,
                            EmailService emailService, @Value("${spring.mail.username}") String storeEmail) {
        this.userRepository = userRepository;
        this.orderMapper = orderMapper;
        this.emailService = emailService;
        this.storeEmail = storeEmail;
    }

    @Override
    public OrderDto createNewOrder(Long userId, OrderDto orderDto) {
        log.info("Creating new order for user " + userId);

        orderDto.setOrderDate(LocalDateTime.now());

        return saveOrderDto(userId, orderDto);
    }


    @Override
    public OrderDto updateOrder(Long userId, Long orderId, OrderDto orderDto) {
        log.info("Updating order...");

        orderDto.setOrderDate(LocalDateTime.now());

        return updateOrderDto(userId, orderId, orderDto);
    }



    @Override
    public List<OrderDto> getOrderList(Long userId) {

        return getUserById(userId).getOrders()
                .stream()
                .map(order -> orderMapper.orderToOrderDto(order))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(Long userId, Long orderId) {
        User user = getUserById(userId);


        return user.getOrders().stream()
                .filter(order -> order.getId().equals(orderId))
                .map(orderMapper::orderToOrderDto)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void deleteOrderById(Long userId, Long orderId) {
        User user = getUserById(userId);

        List<Order> orders = user.getOrders()
                .stream()
                .filter(order -> !(order.getId().equals(orderId)))
                .collect(Collectors.toList());

        user.setOrders(orders);

        userRepository.save(user);
    }


    private User getUserById(Long userId) {
        User user = userRepository.findOne(userId);

        if(user == null){
            throw new ResourceNotFoundException();
        }

        return user;
    }

    private OrderDto updateOrderDto(Long userId, Long orderId, OrderDto orderDto) {
        User user = getUserById(userId);


        Order orderToRemove = user.getOrders()
                .stream()
                .filter(order -> order.getId().equals(orderId))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);

        user.getOrders().remove(orderToRemove);


        orderDto.setUser(user);
        user.addOrder(orderMapper.orderDtoToOrder(orderDto));

        User updatedUser = userRepository.save(user);

        Order updatedOrder = updatedUser.getOrders()
                                .stream()
                                .filter(order -> order.getId().equals(orderId))
                                .findFirst()
                                .orElseThrow(NoSuchElementException::new);



        return orderMapper.orderToOrderDto(updatedOrder);
    }

    private OrderDto saveOrderDto(Long userId, OrderDto orderDto){
        User user = getUserById(userId);
        orderDto.setUser(user);

        user.addOrder(orderMapper.orderDtoToOrder(orderDto));
        User returnedUser = userRepository.save(user);


        Order order = returnedUser.getOrders()
                .stream()
                .max(Comparator.comparing(Order::getId))
                .orElseThrow(NoSuchElementException::new);

        try {
            sendEmails(order, returnedUser);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return orderMapper.orderToOrderDto(order);
    }


    private void sendEmails(Order order, User user) throws MessagingException, InterruptedException {
        emailService.sendEmail
                (
                        EmailDto.builder()
                           .from(storeEmail)
                           .text(generateProductList(order.getProducts()))
                           .to(user.getUsername())
                           .subject("Spring Store order")
                           .user(user)
                           .build()
                );


    }

    private String generateProductList(List<Product> products) {
        StringBuilder builder = new StringBuilder();

        Product tempProduct = null;
        int productAmount = 0;

        for(Product p : products){
            if(tempProduct == null){
                tempProduct = p;
            }

            if(p.getId().equals(tempProduct.getId())){
                productAmount++;
            }else{
                builder.append("id: " + tempProduct.getId() + " name: "
                        + tempProduct.getName() +
                        " price: " + tempProduct.getPrice() + " amount: " + productAmount +"\n");

                productAmount = 1;
                tempProduct = p;
            }


        }



        return builder.toString();
    }

}
