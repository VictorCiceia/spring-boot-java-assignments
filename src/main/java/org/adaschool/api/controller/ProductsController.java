package org.adaschool.api.controller;


import org.adaschool.api.repository.product.Product;
import org.adaschool.api.service.product.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/v1/products/")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(@Autowired ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") final String id) {
        final Optional<Product> product = this.productsService.findById(id);
        return ResponseEntity.ok(product.get());
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(this.productsService.all());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody final Product product) {
        URI createdProductUri = URI.create("");
        return ResponseEntity.created(createdProductUri).body(this.productsService.save(product));
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") final String id, @RequestBody final Product product) {
        return ResponseEntity.ok(this.productsService.update(product, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") final String id) {
        this.productsService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}