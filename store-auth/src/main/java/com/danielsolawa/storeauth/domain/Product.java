package com.danielsolawa.storeauth.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Category category;

    @ManyToOne
    @JsonIgnore
    private Inventory inventory;

    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

}
