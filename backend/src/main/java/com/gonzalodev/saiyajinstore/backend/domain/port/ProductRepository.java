package com.gonzalodev.saiyajinstore.backend.domain.port;

import com.gonzalodev.saiyajinstore.backend.domain.model.Product;

public interface ProductRepository {
    Product save(Product product);
    Iterable<Product> findAll();
    Product findById(Integer id);
    void deleteById(Integer id);
}
