package com.astontech.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductRest {

    private ProductRepo productRepo;

    public ProductRest(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    //CRUD Functions

    @GetMapping("/")
    public Iterable<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productRepo.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productRepo.save(product), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/")
    public void deleteProduct(@RequestBody Product product) {
        productRepo.delete(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productRepo.deleteById(id);
    }

    //Query Params
    @GetMapping()
    public Product findByIdOrSku(@RequestParam(required = false) Integer id,
                                 @RequestParam(required = false) String sku) {
        if (sku != null && !sku.isEmpty()) {
            return productRepo.findBySku(sku).orElseThrow(() -> new ProductNotFoundException(sku));
        }
        else if (id != null) {
            return productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException(Integer.toString(id)));
        }
        else {
            //throw custom exception
            throw new ProductNotFoundException((sku == null ? Integer.toString(id) : sku));
        }
    }

    //Path Params
    @GetMapping("/{id}")
    public Product findByIdInPath(@PathVariable Integer id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepo.findById(id);
        return productOptional.orElseThrow(() -> new ProductNotFoundException(Integer.toString(id)));
    }
}
