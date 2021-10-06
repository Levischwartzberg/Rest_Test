package com.astontech.rest.repositories;

import com.astontech.rest.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends CrudRepository<Product, Integer> {

    Optional<Product> findBySku(String sku);

    Optional<Product> findBySkuOrId(String sku, Integer id);
}
