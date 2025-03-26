package com.gonzalodev.saiyajinstore.backend.infrastructure.rest;

import com.gonzalodev.saiyajinstore.backend.application.OrderService;
import com.gonzalodev.saiyajinstore.backend.domain.model.Order;
import com.gonzalodev.saiyajinstore.backend.domain.model.OrderState;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@Slf4j
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> save(@RequestBody Order order){
        if(order.getState().toString().equals(OrderState.CANCELLED.toString())){
            order.setState(OrderState.CANCELLED);
        } else{
            order.setState(OrderState.CONFIRMED);
        }
        return ResponseEntity.ok(orderService.save(order));
    }

    @PostMapping("/update/order/state")
    public ResponseEntity updateStateById(@RequestParam Integer id, @RequestParam String state){
        orderService.updateStateById(id, state);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Iterable<Order>> findAll(){
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable Integer id){
        return ResponseEntity.ok(orderService.findById(id));
    }

    @GetMapping("/by-user/{id}")
    public ResponseEntity<Iterable<Order>> findByUserId(@PathVariable Integer id){
        return ResponseEntity.ok(orderService.findByUserId(id));
    }
}
