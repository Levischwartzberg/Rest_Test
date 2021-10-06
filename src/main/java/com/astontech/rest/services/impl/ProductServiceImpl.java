package com.astontech.rest.services.impl;

import com.astontech.rest.domain.Product;
import com.astontech.rest.exceptions.FieldNotFoundException;
import com.astontech.rest.exceptions.ProductNotFoundException;
import com.astontech.rest.repositories.ProductRepo;
import com.astontech.rest.services.ProductService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) { this.productRepo = productRepo;}

    @Override
    public Product findBySkuOrID(String sku, Integer id) {
        return productRepo.findBySkuOrId(sku, id)
                .orElseThrow(() -> new ProductNotFoundException((sku == null ? String.valueOf(id) : sku)));
    }

    @Override
    public Product patchProduct(Map<String, Object> updates, Integer id) throws FieldNotFoundException {
        Product  productToPatch = productRepo.findBySkuOrId(null, id).orElseThrow(() -> new ProductNotFoundException(String.valueOf(id)));
        updates.forEach((k, o) -> {
            System.out.println(k + ":" + o);
            try {
                Field nameField = productToPatch.getClass().getDeclaredField(k);
                nameField.setAccessible(true);
                nameField.set(productToPatch, o);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new FieldNotFoundException(k);
            }
        });
        return productRepo.save(productToPatch);
    }

    @Override
    public Iterable<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepo.delete(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepo.deleteById(id);
    }
}
