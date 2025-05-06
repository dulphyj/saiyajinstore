package com.gonzalodev.saiyajinstore.backend.domain.port;

import com.gonzalodev.saiyajinstore.backend.domain.model.Product;

import java.math.BigDecimal;

public interface ProductRepository {
    Product save(Product product);
    Iterable<Product> findAll();
    Product findById(Integer id);
    void deleteById(Integer id);
    Iterable<Product> searchProducts(String name, BigDecimal price, Integer categoryId);

}
