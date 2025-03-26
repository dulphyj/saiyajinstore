package com.gonzalodev.saiyajinstore.backend.application;

import com.gonzalodev.saiyajinstore.backend.domain.model.Category;
import com.gonzalodev.saiyajinstore.backend.domain.port.CategoryRepository;

public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category save(Category category){
        return categoryRepository.save(category);
    }

    public Iterable<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category findById(Integer id){
        return categoryRepository.findById(id);
    }

    public void deleteById(Integer id){
        categoryRepository.deleteById(id);
    }
}
