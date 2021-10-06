package com.astontech.rest.services;

import com.astontech.rest.domain.Product;

public interface ProductService {

    Product findBySkuOrID(String sku, Integer id);

    Iterable<Product> findAll();
    Product saveProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(Product product);
    void deleteProduct(Integer id);
}
