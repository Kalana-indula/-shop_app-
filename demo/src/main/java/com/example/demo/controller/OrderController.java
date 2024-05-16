package com.example.demo.controller;

import com.example.demo.dto.OrderDto;
import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.status(200).body(orderService.getAllOrders());
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id){
        return ResponseEntity.status(200).body(orderService.getOrderById(id));
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody OrderDto orderDto){
        return ResponseEntity.status(201).body(orderService.createOrder(orderDto));
    }
}
