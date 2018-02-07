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
    @Column(length = 1000)
    private String description;

    @ManyToOne
    @JsonIgnore
    private Category category;

    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    private List<Inventory> inventories = new ArrayList<>();

    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

}
