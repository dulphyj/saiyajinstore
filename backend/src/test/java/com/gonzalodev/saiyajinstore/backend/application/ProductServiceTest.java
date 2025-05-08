package com.gonzalodev.saiyajinstore.backend.application;

import com.gonzalodev.saiyajinstore.backend.domain.model.Product;
import com.gonzalodev.saiyajinstore.backend.domain.port.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @Mock
    private CloudinaryService cloudinaryService;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_shouldReturnProduct(){
        LocalDateTime time = LocalDateTime.now();
        Product product = new Product(1, "test", "codeT", "descriptionTest", "url.test", new BigDecimal("50.00"), time, time, 1, 1);

        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.save(product);

        assertEquals(product, result);
        verify(productRepository).save(product);
    }

    @Test
    void findAll_shouldReturnAllProducts(){
        LocalDateTime time = LocalDateTime.now();
        Product product = new Product(1, "test", "codeT", "descriptionTest", "url.test", new BigDecimal("50.00"), time, time, 1, 1);
        Product product2 = new Product(2, "test2", "codeT2", "descriptionTest2", "url.test2", new BigDecimal("50.00"), time, time, 1, 1);

        List<Product> products = Arrays.asList(product, product2);
        when(productRepository.findAll()).thenReturn(products);

        Iterable<Product> result = productService.findAll();

        assertNotNull(result);
        assertEquals(products, result);
        verify(productRepository).findAll();
    }

    @Test
    void serach_shouldReturnFilteredProduct(){
        LocalDateTime time = LocalDateTime.now();
        BigDecimal price = new BigDecimal("50.00");
        Product product = new Product(1, "test", "codeT", "descriptionTest", "url.test", new BigDecimal("50.00"), time, time, 1, 1);
        Product product2 = new Product(2, "test2", "codeT2", "descriptionTest2", "url.test2", new BigDecimal("50.00"), time, time, 1, 1);

        List<Product> products = Arrays.asList(product, product2);
        when(productRepository.searchProducts("test", price, 1)).thenReturn(products);

        Iterable<Product> result = productService.search("test", price, 1);

        assertNotNull(result);
        assertEquals(products, result);
        verify(productRepository).searchProducts("test", price, 1);

    }

    @Test
    void findById_shouldReturnProduct(){
        LocalDateTime time = LocalDateTime.now();
        Product product = new Product(1, "test", "codeT", "descriptionTest", "url.test", new BigDecimal("50.00"), time, time, 1, 1);

        when(productRepository.findById(1)).thenReturn(product);

        Product result = productService.findById(1);

        assertEquals(product, result);
        verify(productRepository).findById(1);
    }

    @Test
    void testUpdateProduct_withImageChange_success() throws Exception {
        Integer productId = 1;
        String oldImageUrl = "http://cloudinary.com/image/oldImage.jpg";
        String newImageUrl = "http://cloudinary.com/image/newImage.jpg";
        String publicId = "oldImage";

        Product existingProduct = new Product();
        existingProduct.setId(productId);
        existingProduct.setUrlImage(oldImageUrl);

        Product updatedProduct = new Product();
        updatedProduct.setName("Updated name");

        MockMultipartFile file = new MockMultipartFile("image", "test.jpg", "image/jpeg", "fake image".getBytes());

        when(productRepository.findById(productId)).thenReturn(existingProduct);
        when(cloudinaryService.extracPublicId(oldImageUrl)).thenReturn(publicId);
        doNothing().when(cloudinaryService).deleteImage(publicId);
        when(cloudinaryService.uploadImage(file)).thenReturn(newImageUrl);
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Product result = productService.updateProduct(productId, updatedProduct, file);

        assertEquals(productId, result.getId());
        assertEquals(newImageUrl, result.getUrlImage());
        verify(productRepository).save(any(Product.class));
        verify(cloudinaryService).deleteImage(publicId);
        verify(cloudinaryService).uploadImage(file);
    }

    @Test
    void testDelete_existingProductWithNonDefaultImage_success() throws Exception {
        Integer productId = 1;
        String imageUrl = "https://res.cloudinary.com/demo/image/upload/v1/abc.jpg";
        String publicId = "abc";

        Product product = new Product();
        product.setId(productId);
        product.setUrlImage(imageUrl);

        when(productRepository.findById(productId)).thenReturn(product);
        when(cloudinaryService.extracPublicId(imageUrl)).thenReturn(publicId);
        doNothing().when(cloudinaryService).deleteImage(publicId);
        doNothing().when(productRepository).deleteById(productId);

        productService.delete(productId);

        verify(productRepository).findById(productId);
        verify(cloudinaryService).extracPublicId(imageUrl);
        verify(cloudinaryService).deleteImage(publicId);
        verify(productRepository).deleteById(productId);
    }

}
