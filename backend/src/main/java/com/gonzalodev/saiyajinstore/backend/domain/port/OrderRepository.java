package com.gonzalodev.saiyajinstore.backend.domain.port;

import com.gonzalodev.saiyajinstore.backend.domain.model.Order;

public interface OrderRepository {
    Order save(Order order);
    Order findById(Integer id);
    Iterable<Order> findAll();
    Iterable<Order> findByUserId(Integer userId);
    void updateStateById(Integer id, String state);
}
