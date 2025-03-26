package com.gonzalodev.saiyajinstore.backend.domain.port;

import com.gonzalodev.saiyajinstore.backend.domain.model.Category;

public interface CategoryRepository {
    Category save(Category category);
    Iterable<Category> findAll();
    Category findById(Integer id);
    void deleteById(Integer id);
}
