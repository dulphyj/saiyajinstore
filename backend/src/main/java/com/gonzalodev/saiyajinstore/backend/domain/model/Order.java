package com.gonzalodev.saiyajinstore.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Order {
    private Integer id;
    private LocalDateTime dateCreated;
    private List<OrderProduct> orderProducts;
    private OrderState state;
    private Integer userId;

    public Order() {
        orderProducts = new ArrayList<>();
    }

    public BigDecimal getTotalOrderPrice(){
        return orderProducts.stream().map( orderProduct -> orderProduct.getTotalItem()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
