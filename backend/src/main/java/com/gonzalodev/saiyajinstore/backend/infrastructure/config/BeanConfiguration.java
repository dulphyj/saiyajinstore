package com.gonzalodev.saiyajinstore.backend.infrastructure.config;

import com.gonzalodev.saiyajinstore.backend.application.CategoryService;
import com.gonzalodev.saiyajinstore.backend.application.OrderService;
import com.gonzalodev.saiyajinstore.backend.application.ProductService;
import com.gonzalodev.saiyajinstore.backend.application.UserService;
import com.gonzalodev.saiyajinstore.backend.domain.port.CategoryRepository;
import com.gonzalodev.saiyajinstore.backend.domain.port.OrderRepository;
import com.gonzalodev.saiyajinstore.backend.domain.port.ProductRepository;
import com.gonzalodev.saiyajinstore.backend.domain.port.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public UserService userService(UserRepository userRepository){
        return new UserService(userRepository);
    }

    @Bean
    public CategoryService categoryService(CategoryRepository categoryRepository){
        return new CategoryService(categoryRepository);
    }

    @Bean
    public ProductService productService(ProductRepository productRepository){
        return new ProductService(productRepository);
    }

    @Bean
    public OrderService orderService(OrderRepository orderRepository){
        return new OrderService(orderRepository);
    }
}
