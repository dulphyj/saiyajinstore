package com.gonzalodev.saiyajinstore.backend.infrastructure.rest;

import com.gonzalodev.saiyajinstore.backend.application.ProductService;
import com.gonzalodev.saiyajinstore.backend.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/products")
@Slf4j
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/admin")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        log.info("Product with id: {} created.", product.getId());
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll(){
        log.info("Products found.");
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Integer id){
        log.info("Product with id: {} found.", id);
        return ResponseEntity.ok(productService.findById(id));
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Integer id){
        log.warn("Product with id: {} deleted.", id);
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/admin/update/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Integer id,
            @RequestPart("product") Product product,
            @RequestPart(value = "file", required = false) MultipartFile file){
        return ResponseEntity.ok(productService.updateProduct(id, product, file));
    }
}
