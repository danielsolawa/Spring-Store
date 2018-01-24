package com.danielsolawa.storeauth.dtos;


import com.danielsolawa.storeauth.domain.Category;
import com.danielsolawa.storeauth.domain.Inventory;
import com.danielsolawa.storeauth.domain.Order;
import lombok.Data;

@Data
public class ProductDto {

    private Long id;
    private String name;
    private Double price;
    private Category category;
    private Inventory inventory;
    private Order order;
}
