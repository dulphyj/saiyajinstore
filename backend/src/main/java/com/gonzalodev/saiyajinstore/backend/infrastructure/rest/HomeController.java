package com.gonzalodev.saiyajinstore.backend.infrastructure.rest;

import com.gonzalodev.saiyajinstore.backend.application.ProductService;
import com.gonzalodev.saiyajinstore.backend.application.UserService;
import com.gonzalodev.saiyajinstore.backend.domain.model.Product;
import com.gonzalodev.saiyajinstore.backend.domain.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/home")
@CrossOrigin(origins = "http://localhost:4200/")
@AllArgsConstructor
public class HomeController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }
}
