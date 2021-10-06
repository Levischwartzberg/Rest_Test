package com.astontech.rest.controllers;

import com.astontech.rest.domain.Product;
import com.astontech.rest.exceptions.ProductNotFoundException;
import com.astontech.rest.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductRest {

    private ProductService productService;

    public ProductRest(ProductService productService) { this.productService = productService; }

    //CRUD Functions

    @GetMapping("/")
    public Iterable<Product> getAllProducts() {
        return productService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdateDynamic(
            @RequestBody Map<String, Object> updates,
            @PathVariable Integer id) {
        return new ResponseEntity<>(
                productService.patchProduct(updates, id),
                HttpStatus.ACCEPTED
        );
    }


    @DeleteMapping("/")
    public void deleteProduct(@RequestBody Product product) {
        productService.deleteProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }

    //Query Params
    @GetMapping()
    public Product findByIdOrSku(@RequestParam(required = false) Integer id,
                                 @RequestParam(required = false) String sku) {
        return productService.findBySkuOrID(sku, id);
    }

    //Path Params
    @GetMapping("/{id}")
    public ResponseEntity<Product> findByIdInPath(@PathVariable Integer id) throws ProductNotFoundException {
        return ResponseEntity.ok(productService.findBySkuOrID(null, id));
    }
}
