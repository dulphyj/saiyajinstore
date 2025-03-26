package com.gonzalodev.saiyajinstore.backend.infrastructure.adapter;

import com.gonzalodev.saiyajinstore.backend.infrastructure.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CategoryCRUDRepository extends CrudRepository<CategoryEntity, Integer> {
}
