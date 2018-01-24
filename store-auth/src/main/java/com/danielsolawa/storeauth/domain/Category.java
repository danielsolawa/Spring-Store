package com.danielsolawa.storeauth.domain;


import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Product product;
}
