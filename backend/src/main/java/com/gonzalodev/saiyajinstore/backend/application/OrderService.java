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
        try {
            return orderRepository.save(order);
        } catch (Exception e){
            throw new RuntimeException("Error saving order", e);
        }
    }

    public Iterable<Order> findAll(){
        return orderRepository.findAll();
    }

    public Iterable<Order> findByUserId(Integer userId){
        try {
            return orderRepository.findByUserId(userId);
        } catch (Exception e){
            throw new RuntimeException("Error finding order", e);
        }
    }

    public void updateStateById(Integer id, String state){
        try {
            orderRepository.updateStateById(id, state);
        } catch (Exception e){
            throw new RuntimeException("Error updating order", e);
        }
    }

    public Order findById(Integer id){
        try {
            return orderRepository.findById(id);
        } catch (Exception e){
            throw new RuntimeException("Error finding order", e);
        }
    }

}
