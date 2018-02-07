package com.danielsolawa.storeauth.dtos;


import com.danielsolawa.storeauth.domain.Category;
import com.danielsolawa.storeauth.domain.Inventory;
import com.danielsolawa.storeauth.domain.Order;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDto {

    private Long id;
    private String name;
    private Double price;
    private String description;
    private Category category;
    private List<Inventory> inventories = new ArrayList<>();
    private List<Order> orders;
}
