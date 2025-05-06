package com.gonzalodev.saiyajinstore.backend.infrastructure.adapter;


import com.gonzalodev.saiyajinstore.backend.domain.model.Product;
import com.gonzalodev.saiyajinstore.backend.domain.port.ProductRepository;
import com.gonzalodev.saiyajinstore.backend.infrastructure.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
@AllArgsConstructor
public class ProductCRUDRepositoryImp implements ProductRepository {
    private final ProductCRUDRepository productCRUDRepository;
    private final ProductMapper productMapper;

    @Override
    public Product save(Product product) {
        return productMapper.toProduct(productCRUDRepository.save(productMapper.toProductEntity(product)));
    }

    @Override
    public Iterable<Product> findAll() {
        return productMapper.toProductList(productCRUDRepository.findAll());
    }

    @Override
    public Product findById(Integer id) {
        return productMapper.toProduct(productCRUDRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Product with id: "+id+" no found")
        ));
    }

    @Override
    public void deleteById(Integer id) {
        productCRUDRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Product with id: "+id+" no found")
        );
        productCRUDRepository.deleteById(id);
    }

    @Override
    public Iterable<Product> searchProducts(String name, BigDecimal price, Integer categoryId) {
        return productMapper.toProductList(productCRUDRepository.search(name, price, categoryId));
    }
}
