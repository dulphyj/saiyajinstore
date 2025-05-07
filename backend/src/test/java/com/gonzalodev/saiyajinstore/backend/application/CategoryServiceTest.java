package com.gonzalodev.saiyajinstore.backend.application;

import com.gonzalodev.saiyajinstore.backend.domain.model.Category;
import com.gonzalodev.saiyajinstore.backend.domain.port.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldReturnSavedCategory() {
        Category category = new Category(1, "test", LocalDateTime.parse("2025-06-05T00:00:00"), LocalDateTime.parse("2025-06-05T01:00:00"));

        when(categoryRepository.save(category)).thenReturn(category);

        Category result = categoryService.save(category);

        assertEquals(category, result);
        verify(categoryRepository).save(category);
    }

    @Test
    void findAll_ShouldReturnAllCategories() {
        Category c1 = new Category(1, "test", LocalDateTime.parse("2025-06-05T00:00:00"), LocalDateTime.parse("2025-06-05T01:00:00"));
        Category c2 = new Category(2, "other", LocalDateTime.parse("2025-06-05T00:00:00"), LocalDateTime.parse("2025-06-05T01:00:00"));

        List<Category> categories = Arrays.asList(c1, c2);
        when(categoryRepository.findAll()).thenReturn(categories);

        Iterable<Category> result = categoryService.findAll();

        assertNotNull(result);
        assertEquals(categories, result);
        verify(categoryRepository).findAll();
    }

    @Test
    void findById_ShouldReturnCategory() {
        Category category = new Category(1, "test", LocalDateTime.parse("2025-06-05T00:00:00"), LocalDateTime.parse("2025-06-05T01:00:00"));

        when(categoryRepository.findById(1)).thenReturn(category);

        Category result = categoryService.findById(1);

        assertEquals(category, result);
        verify(categoryRepository).findById(1);
    }

    @Test
    void deleteById_ShouldInvokeRepositoryDelete() {
        Integer id = 1;

        doNothing().when(categoryRepository).deleteById(id);

        categoryService.deleteById(id);

        verify(categoryRepository).deleteById(id);
    }
}
