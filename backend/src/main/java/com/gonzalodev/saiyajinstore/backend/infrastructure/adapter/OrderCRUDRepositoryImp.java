package com.gonzalodev.saiyajinstore.backend.infrastructure.adapter;

import com.gonzalodev.saiyajinstore.backend.domain.model.Order;
import com.gonzalodev.saiyajinstore.backend.domain.model.OrderState;
import com.gonzalodev.saiyajinstore.backend.domain.port.OrderRepository;
import com.gonzalodev.saiyajinstore.backend.infrastructure.entity.OrderEntity;
import com.gonzalodev.saiyajinstore.backend.infrastructure.entity.UserEntity;
import com.gonzalodev.saiyajinstore.backend.infrastructure.mapper.OrderMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class OrderCRUDRepositoryImp implements OrderRepository {

    private final OrderMapper orderMapper;
    private final OrderCRUDRepository orderCRUDRepository;

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = orderMapper.toOrderEntity(order);
        orderEntity.getOrderProducts().forEach(
                orderProductEntity -> orderProductEntity.setOrderEntity(orderEntity)
        );
        return orderMapper.toOrder(orderCRUDRepository.save(orderEntity));
    }

    @Override
    public Order findById(Integer id) {
        return orderMapper.toOrder(orderCRUDRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Order with id:{} mp fpund." + id)
        ));
    }

    @Override
    public Iterable<Order> findAll() {
        return orderMapper.toOrderList(orderCRUDRepository.findAll());
    }

    @Override
    public Iterable<Order> findByUserId(Integer userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        return orderMapper.toOrderList(orderCRUDRepository.findByUserEntity(userEntity));
    }

    @Override
    public void updateStateById(Integer id, String state) {
        if(state.equals(OrderState.CANCELLED)){
            orderCRUDRepository.updateStateById(id, OrderState.CANCELLED);
        } else {
            orderCRUDRepository.updateStateById(id, OrderState.CONFIRMED);
        }
    }
}
