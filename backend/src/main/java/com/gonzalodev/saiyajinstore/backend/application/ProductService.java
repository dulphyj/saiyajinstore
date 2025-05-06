package com.gonzalodev.saiyajinstore.backend.application;

import com.gonzalodev.saiyajinstore.backend.config.AppConstants;
import com.gonzalodev.saiyajinstore.backend.domain.model.Product;
import com.gonzalodev.saiyajinstore.backend.domain.port.ProductRepository;
import com.gonzalodev.saiyajinstore.backend.infrastructure.entity.ProductEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CloudinaryService cloudinaryService;

    private static final String image_default = AppConstants.DEFAULT_CLOUDINARY_IMAGE_PUBLIC_ID;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Iterable<Product> findAll(){
        return productRepository.findAll();
    }

    public Iterable<Product> search(String name, BigDecimal price, Integer categoryId){
        return productRepository.searchProducts(name, price,categoryId);
    }

    public Product findById(Integer id){
        return productRepository.findById(id);
    }

    public Product updateProduct(Integer id, Product updateProduct, MultipartFile file){
        Optional<Product> existingProductOpt = Optional.ofNullable(productRepository.findById(id));
        if(existingProductOpt.isEmpty()){
            throw new RuntimeException("Product not found");
        }

        Product existingProduct = existingProductOpt.get();
        String oldImageUrl = existingProduct.getUrlImage();
        if(file != null && !file.isEmpty()){
            String publicId = cloudinaryService.extracPublicId(oldImageUrl);
            if(!image_default.equals(publicId)){
                try {
                    cloudinaryService.deleteImage(publicId);
                } catch (Exception e){
                    throw new RuntimeException("Failed to delete old image from Cloud", e);
                }
            }
            try {
                String newImageUrl = cloudinaryService.uploadImage(file);
                updateProduct.setUrlImage(newImageUrl);
            } catch (IOException e){
                throw new RuntimeException("Failed to upload image", e);
            }
        } else {
            updateProduct.setUrlImage(oldImageUrl);
        }

        updateProduct.setId(id);
        return productRepository.save(updateProduct);
    }

    public void delete(Integer id) {
        Optional<Product> productOpt = Optional.ofNullable(productRepository.findById(id));
        if(productOpt.isPresent()){
            Product product = productOpt.get();
            String imageUrl = product.getUrlImage();
            String publicId = cloudinaryService.extracPublicId(imageUrl);
            if(!image_default.equals(publicId)){
                try {
                    cloudinaryService.deleteImage(publicId);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to delete image from Cloudinary", e);
                }
            }
            productRepository.deleteById(id);
        }   else {
            throw new RuntimeException("Product not found");
        }
    }
}
