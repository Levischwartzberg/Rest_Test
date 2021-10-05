package com.astontech.rest;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Integer> {

    Product findBySku(String sku);
}
