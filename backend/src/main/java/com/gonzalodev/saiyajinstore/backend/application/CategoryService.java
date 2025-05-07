package com.gonzalodev.saiyajinstore.backend.application;

import com.gonzalodev.saiyajinstore.backend.domain.model.Category;
import com.gonzalodev.saiyajinstore.backend.domain.port.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category save(Category category){
        try {
            return categoryRepository.save(category);
        } catch (Exception e){
            throw new RuntimeException("Error saving category", e);
        }
    }

    public Iterable<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category findById(Integer id){
        try {
            return categoryRepository.findById(id);
        } catch (Exception e){
            throw new RuntimeException("Error finding category", e);
        }
    }

    public void deleteById(Integer id){
        try {
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting category with id " + id, e);
        }
    }
}
