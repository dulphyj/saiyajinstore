package com.gonzalodev.saiyajinstore.backend.infrastructure.rest;

import com.gonzalodev.saiyajinstore.backend.application.CategoryService;
import com.gonzalodev.saiyajinstore.backend.application.ProductService;
import com.gonzalodev.saiyajinstore.backend.application.UserService;
import com.gonzalodev.saiyajinstore.backend.domain.model.Category;
import com.gonzalodev.saiyajinstore.backend.domain.model.Product;
import com.gonzalodev.saiyajinstore.backend.domain.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@ControllerAdvice
@RestController
@RequestMapping("/api/home")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class HomeController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Product>> serach(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) BigDecimal price,
            @RequestParam(required = false) Integer categoryId){
        return ResponseEntity.ok(productService.search(name, price, categoryId));
    }

    @GetMapping("/categories")
    public ResponseEntity<Iterable<Category>> findAllCategories(){
        return ResponseEntity.ok(categoryService.findAll());
    }
}
