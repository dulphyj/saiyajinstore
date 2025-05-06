package com.gonzalodev.saiyajinstore.backend.infrastructure.adapter;

import com.gonzalodev.saiyajinstore.backend.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;

public interface ProductCRUDRepository extends CrudRepository<ProductEntity, Integer> {
    @Query("SELECT p FROM ProductEntity p WHERE " +
            "(:name IS NULL OR :name = '' OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:price IS NULL OR p.price = :price) AND " +
            "(:categoryId IS NULL OR p.categoryEntity.id = :categoryId)")
    Iterable<ProductEntity> search(String name, BigDecimal price, Integer categoryId);
}
