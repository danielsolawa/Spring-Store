package com.danielsolawa.storeauth.dtos;


import com.danielsolawa.storeauth.domain.Product;
import com.danielsolawa.storeauth.domain.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDto {

    private Long id;
    private LocalDateTime orderDate;
    private User user;
    private List<Product> products = new ArrayList<>();
}
