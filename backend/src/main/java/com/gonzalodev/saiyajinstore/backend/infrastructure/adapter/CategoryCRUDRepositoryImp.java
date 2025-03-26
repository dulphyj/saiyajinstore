package com.gonzalodev.saiyajinstore.backend.infrastructure.adapter;

import com.gonzalodev.saiyajinstore.backend.domain.model.Category;
import com.gonzalodev.saiyajinstore.backend.domain.port.CategoryRepository;
import com.gonzalodev.saiyajinstore.backend.infrastructure.mapper.CategoryMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CategoryCRUDRepositoryImp implements CategoryRepository {
    private final CategoryCRUDRepository categoryCRUDRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Category save(Category category) {
        return categoryMapper.toCategory(categoryCRUDRepository.save(categoryMapper.toCategoryEntity(category)));
    }

    @Override
    public Iterable<Category> findAll() {
        return categoryMapper.toCategoryList(categoryCRUDRepository.findAll());
    }

    @Override
    public Category findById(Integer id) {
        return categoryMapper.toCategory(categoryCRUDRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Category with id: "+id+" not found")
        ));
    }

    @Override
    public void deleteById(Integer id) {
        categoryCRUDRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Category with id: "+id+" not found")
        );
        categoryCRUDRepository.deleteById(id);
    }
}
