package com.danielsolawa.storeauth.domain;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;

    @OneToOne(mappedBy = "product")
    private Category category;

    @ManyToOne
    private Inventory inventory;

    @ManyToOne
    private Order order;

}
