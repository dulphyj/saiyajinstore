package com.gonzalodev.saiyajinstore.backend.infrastructure.adapter;

import com.gonzalodev.saiyajinstore.backend.domain.model.Category;
import com.gonzalodev.saiyajinstore.backend.infrastructure.entity.CategoryEntity;
import com.gonzalodev.saiyajinstore.backend.infrastructure.mapper.CategoryMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryCRUDRepositoryImpTest {
   @Mock
    private CategoryCRUDRepository categoryCRUDRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryCRUDRepositoryImp categoryCRUDRepositoryImp;

    @Test
    void save_shouldReturnCategory(){
        LocalDateTime time = LocalDateTime.now();
        Category category = new Category(1, "test", time, time);
        CategoryEntity categoryEntity = new CategoryEntity(1, "test", time, time);

        when(categoryMapper.toCategoryEntity(category)).thenReturn(categoryEntity);
        when(categoryCRUDRepository.save(categoryEntity)).thenReturn(categoryEntity);
        when(categoryMapper.toCategory(categoryEntity)).thenReturn(category);

        Category result = categoryCRUDRepositoryImp.save(category);

        assertEquals(category, result);
        verify(categoryCRUDRepository).save(categoryEntity);
        verify(categoryMapper).toCategory(categoryEntity);
    }

    @Test
    void  findAll_ShouldReturnAllCategories(){
        LocalDateTime time = LocalDateTime.now();
        Category category = new Category(1, "test", time, time);
        Category category2 = new Category(2, "test2", time, time);
        List<Category> categories = List.of(category, category2);

        CategoryEntity categoryEntity = new CategoryEntity(1, "test", time, time);
        CategoryEntity categoryEntity2 = new CategoryEntity(2, "test2", time, time);
        List<CategoryEntity> categoriesEntities = List.of(categoryEntity, categoryEntity2);

        when(categoryCRUDRepository.findAll()).thenReturn(categoriesEntities);
        when(categoryMapper.toCategoryList(categoriesEntities)).thenReturn(categories);

        Iterable<Category> result = categoryCRUDRepositoryImp.findAll();

        assertEquals(categories, result);
        verify(categoryCRUDRepository).findAll();
        verify(categoryMapper).toCategoryList(categoriesEntities);
    }

    @Test
    void FindById_shouldReturnCategory(){
        int id = 1;
        LocalDateTime time = LocalDateTime.now();
        Category category = new Category(id, "test", time, time);
        CategoryEntity categoryEntity = new CategoryEntity(id, "test", time, time);

        when(categoryCRUDRepository.findById(id)).thenReturn(java.util.Optional.of(categoryEntity));
        when(categoryMapper.toCategory(categoryEntity)).thenReturn(category);

        Category result = categoryCRUDRepositoryImp.findById(id);

        assertEquals(category, result);
        verify(categoryCRUDRepository).findById(id);
        verify(categoryMapper).toCategory(categoryEntity);
    }

    @Test
    void deleteById_shouldDelete(){
        int id = 1;
        LocalDateTime time = LocalDateTime.now();
        CategoryEntity categoryEntity = new CategoryEntity(id, "test", time, time);

        when(categoryCRUDRepository.findById(id)).thenReturn(java.util.Optional.of(categoryEntity));

        categoryCRUDRepositoryImp.deleteById(id);

        verify(categoryCRUDRepository).findById(id);
        verify(categoryCRUDRepository).deleteById(id);
    }

}
