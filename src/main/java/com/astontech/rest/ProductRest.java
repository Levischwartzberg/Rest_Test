package com.astontech.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductRest {

    private ProductRepo productRepo;

    public ProductRest(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @GetMapping("/")
    public Iterable<Product> getAllProducts() {
        return productRepo.findAll();
    }
}
