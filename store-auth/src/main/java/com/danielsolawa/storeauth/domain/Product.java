package com.danielsolawa.storeauth.domain;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Inventory inventory;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders = new ArrayList<>();

}
