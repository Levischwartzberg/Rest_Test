package com.astontech.rest.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String sku;

    private String description;
    private Integer quantity;
    private Float price;
    private Float weight;
    private String dimensions;
}
