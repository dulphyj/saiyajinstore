package com.gonzalodev.saiyajinstore.backend.application;

import com.gonzalodev.saiyajinstore.backend.domain.model.Order;
import com.gonzalodev.saiyajinstore.backend.domain.model.OrderProduct;
import com.gonzalodev.saiyajinstore.backend.domain.model.OrderState;
import com.gonzalodev.saiyajinstore.backend.domain.port.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldReturnSavedOrder(){
        List<OrderProduct> orderProducts = List.of(
                new OrderProduct(1, new BigDecimal("2"), new BigDecimal("50.00"), 1),
                new OrderProduct(2, new BigDecimal("1"), new BigDecimal("30.00"), 2)
        );
        Order order = new Order(1, LocalDateTime.parse("2025-06-05T00:00:00"), orderProducts, OrderState.CANCELLED, 1);

        when(orderRepository.save(order)).thenReturn(order);

        Order result = orderService.save(order);

        assertEquals(order, result);
        verify(orderRepository).save(order);
    }

    @Test
    void findAll_ShouldReturnAllOrders(){
        List<OrderProduct> orderProducts = List.of(
                new OrderProduct(1, new BigDecimal("2"), new BigDecimal("50.00"), 1),
                new OrderProduct(2, new BigDecimal("1"), new BigDecimal("30.00"), 2)
        );
        Order order = new Order(1, LocalDateTime.parse("2025-06-05T00:00:00"), orderProducts, OrderState.CANCELLED, 1);

        List<OrderProduct> orderProducts2 = List.of(
                new OrderProduct(3, new BigDecimal("2"), new BigDecimal("50.00"), 1),
                new OrderProduct(4, new BigDecimal("1"), new BigDecimal("30.00"), 2)
        );
        Order order2 = new Order(2, LocalDateTime.parse("2025-06-05T00:00:00"), orderProducts, OrderState.CANCELLED, 1);

        List<Order> orders = Arrays.asList(order, order2);
        when(orderRepository.findAll()).thenReturn(orders);

        Iterable<Order> result = orderService.findAll();

        assertNotNull(result);
        assertEquals(orders, result);
        verify(orderRepository).findAll();
    }

    @Test
    void findByUserId_ShouldReturnOrderByUserId(){
        List<OrderProduct> orderProducts = List.of(
                new OrderProduct(1, new BigDecimal("2"), new BigDecimal("50.00"), 1),
                new OrderProduct(2, new BigDecimal("1"), new BigDecimal("30.00"), 2)
        );
        Order order = new Order(1, LocalDateTime.parse("2025-06-05T00:00:00"), orderProducts, OrderState.CANCELLED, 1);

        List<OrderProduct> orderProducts2 = List.of(
                new OrderProduct(3, new BigDecimal("2"), new BigDecimal("50.00"), 1),
                new OrderProduct(4, new BigDecimal("1"), new BigDecimal("30.00"), 2)
        );
        Order order2 = new Order(2, LocalDateTime.parse("2025-06-05T00:00:00"), orderProducts, OrderState.CANCELLED, 1);

        List<Order> orders = Arrays.asList(order, order2);
        when(orderRepository.findByUserId(1)).thenReturn(orders);

        Iterable<Order> result = orderService.findByUserId(1);

        assertNotNull(result);
        assertEquals(orders, result);
        verify(orderRepository).findByUserId(1);

    }

    @Test
    void updateStateById_shouldReturnOrderUpdated(){
        List<OrderProduct> orderProducts = List.of(
                new OrderProduct(1, new BigDecimal("2"), new BigDecimal("50.00"), 1),
                new OrderProduct(2, new BigDecimal("1"), new BigDecimal("30.00"), 2)
        );
        Order order = new Order(1, LocalDateTime.parse("2025-06-05T00:00:00"), orderProducts, OrderState.CANCELLED, 1);

        doNothing().when(orderRepository).updateStateById(1, "CONFIRMED");

        orderService.updateStateById(1, "CONFIRMED");

        verify(orderRepository).updateStateById(1, "CONFIRMED");
    }

    @Test
    void findById_ShouldReturnOrderById(){
        List<OrderProduct> orderProducts = List.of(
                new OrderProduct(1, new BigDecimal("2"), new BigDecimal("50.00"), 1),
                new OrderProduct(2, new BigDecimal("1"), new BigDecimal("30.00"), 2)
        );
        Order order = new Order(1, LocalDateTime.parse("2025-06-05T00:00:00"), orderProducts, OrderState.CANCELLED, 1);

        when(orderRepository.findById(1)).thenReturn(order);

        Order result = orderService.findById(1);

        assertEquals(order, result);
        verify(orderRepository).findById(1);
    }
}
