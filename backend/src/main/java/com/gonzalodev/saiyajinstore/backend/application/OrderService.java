package com.gonzalodev.saiyajinstore.backend.application;

import com.gonzalodev.saiyajinstore.backend.domain.model.Order;
import com.gonzalodev.saiyajinstore.backend.domain.port.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order save (Order order){
        return orderRepository.save(order);
    }

    public Iterable<Order> findAll(){
        return orderRepository.findAll();
    }

    public Iterable<Order> findByUserId(Integer userId){
        return orderRepository.findByUserId(userId);
    }

    public void updateStateById(Integer id, String state){
        orderRepository.updateStateById(id, state);
    }

    public Order findById(Integer id){
        return orderRepository.findById(id);
    }

}
