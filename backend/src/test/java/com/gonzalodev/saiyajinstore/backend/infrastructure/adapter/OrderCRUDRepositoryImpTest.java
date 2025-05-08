package com.gonzalodev.saiyajinstore.backend.infrastructure.adapter;

import com.gonzalodev.saiyajinstore.backend.domain.model.Order;
import com.gonzalodev.saiyajinstore.backend.domain.model.OrderProduct;
import com.gonzalodev.saiyajinstore.backend.domain.model.OrderState;
import com.gonzalodev.saiyajinstore.backend.infrastructure.entity.OrderEntity;
import com.gonzalodev.saiyajinstore.backend.infrastructure.entity.OrderProductEntity;
import com.gonzalodev.saiyajinstore.backend.infrastructure.entity.UserEntity;
import com.gonzalodev.saiyajinstore.backend.infrastructure.mapper.OrderMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderCRUDRepositoryImpTest {

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private OrderCRUDRepository orderCRUDRepository;

    @InjectMocks
    private OrderCRUDRepositoryImp orderRepositoryImp;

    private OrderEntity orderEntity;
    private Order order;
    private LocalDateTime time;

    @BeforeEach
    void setUp() {
        time = LocalDateTime.now();

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);

        OrderProductEntity orderProductEntity = new OrderProductEntity();
        orderProductEntity.setId(1);
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setId(1);

        orderEntity = new OrderEntity(1, time, OrderState.CANCELLED, userEntity, List.of(orderProductEntity));
        order = new Order(1, time, List.of(orderProduct),OrderState.CANCELLED, 1);
    }

    @Test
    void save_ShouldReturnSavedOrder() {
        when(orderMapper.toOrderEntity(order)).thenReturn(orderEntity);
        when(orderCRUDRepository.save(orderEntity)).thenReturn(orderEntity);
        when(orderMapper.toOrder(orderEntity)).thenReturn(order);

        Order result = orderRepositoryImp.save(order);

        assertEquals(order, result);
        assertEquals(order.getId(), result.getId());
        verify(orderCRUDRepository).save(orderEntity);
    }

    @Test
    void findById_ShouldReturnOrder_WhenExists() {
        when(orderCRUDRepository.findById(1)).thenReturn(Optional.of(orderEntity));
        when(orderMapper.toOrder(orderEntity)).thenReturn(order);

        Order result = orderRepositoryImp.findById(1);

        assertEquals(order, result);
    }


    @Test
    void findAll_ShouldReturnOrderList() {
        List<OrderEntity> entities = List.of(orderEntity);
        List<Order> orders = List.of(order);

        when(orderCRUDRepository.findAll()).thenReturn(entities);
        when(orderMapper.toOrderList(entities)).thenReturn(orders);

        Iterable<Order> result = orderRepositoryImp.findAll();

        assertEquals(orders, result);
    }

    @Test
    void findByUserId_ShouldReturnUserOrders() {
        List<OrderEntity> entities = List.of(orderEntity);
        List<Order> orders = List.of(order);

        when(orderCRUDRepository.findByUserEntity(any(UserEntity.class))).thenReturn(entities);
        when(orderMapper.toOrderList(entities)).thenReturn(orders);

        Iterable<Order> result = orderRepositoryImp.findByUserId(1);

        assertEquals(orders, result);
    }

    @Test
    void updateStateById_ShouldUpdateToCancelled() {
        orderRepositoryImp.updateStateById(1, "CONFIRMED");

        verify(orderCRUDRepository).updateStateById(1, OrderState.CONFIRMED);
    }
}
