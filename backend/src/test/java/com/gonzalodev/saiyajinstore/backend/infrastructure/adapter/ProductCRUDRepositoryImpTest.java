package com.gonzalodev.saiyajinstore.backend.infrastructure.adapter;

import com.gonzalodev.saiyajinstore.backend.domain.model.Product;
import com.gonzalodev.saiyajinstore.backend.infrastructure.entity.CategoryEntity;
import com.gonzalodev.saiyajinstore.backend.infrastructure.entity.ProductEntity;
import com.gonzalodev.saiyajinstore.backend.infrastructure.entity.UserEntity;
import com.gonzalodev.saiyajinstore.backend.infrastructure.mapper.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductCRUDRepositoryImpTest {

    @Mock
    private ProductCRUDRepository productCRUDRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductCRUDRepositoryImp productRepositoryImp;

    private ProductEntity productEntity;
    private Product product;
    private LocalDateTime time;

    @BeforeEach
    void setUp() {
        time = LocalDateTime.now();
        UserEntity userEntity = new UserEntity();
        CategoryEntity categoryEntity = new CategoryEntity();
        productEntity = new ProductEntity(1, "test", "test", "test", "test", BigDecimal.TEN, time, time, userEntity, categoryEntity);
        product = new Product(1, "test", "test", "test", "test", BigDecimal.TEN, time, time, 1, 1);
    }

    @Test
    void save_ShouldReturnSavedProduct() {

        when(productMapper.toProductEntity(product)).thenReturn(productEntity);
        when(productCRUDRepository.save(productEntity)).thenReturn(productEntity);
        when(productMapper.toProduct(productEntity)).thenReturn(product);

        Product result = productRepositoryImp.save(product);

        assertEquals(product, result);
    }

    @Test
    void findAll_ShouldReturnProductList() {
        List<ProductEntity> productEntities = List.of(productEntity);
        List<Product> products = List.of(product);

        when(productCRUDRepository.findAll()).thenReturn(productEntities);
        when(productMapper.toProductList(productEntities)).thenReturn(products);

        Iterable<Product> result = productRepositoryImp.findAll();

        assertEquals(products, result);
    }

    @Test
    void findById_ShouldReturnProduct_WhenExists() {

        when(productCRUDRepository.findById(1)).thenReturn(Optional.of(productEntity));
        when(productMapper.toProduct(productEntity)).thenReturn(product);

        Product result = productRepositoryImp.findById(1);

        assertEquals(product, result);
    }

    @Test
    void deleteById_ShouldCallDelete_WhenProductExists() {

        when(productCRUDRepository.findById(1)).thenReturn(Optional.of(productEntity));

        productRepositoryImp.deleteById(1);

        verify(productCRUDRepository).deleteById(1);
    }

    @Test
    void searchProducts_ShouldReturnFilteredProducts() {
        List<ProductEntity> productEntities = List.of(productEntity);
        List<Product> products = List.of(product);

        when(productCRUDRepository.search("test", BigDecimal.TEN, 1)).thenReturn(productEntities);
        when(productMapper.toProductList(productEntities)).thenReturn(products);

        Iterable<Product> result = productRepositoryImp.searchProducts("test", BigDecimal.TEN, 1);

        assertEquals(products, result);
    }
}

