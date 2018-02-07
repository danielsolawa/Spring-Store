package com.danielsolawa.storeauth.dtos;

import com.danielsolawa.storeauth.domain.Product;
import com.danielsolawa.storeauth.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InventoryDto {

    private Long id;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;
    private List<Product> products = new ArrayList<>();
    private Long userId;

}
