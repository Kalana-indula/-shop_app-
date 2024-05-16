package com.example.demo.service;

import com.example.demo.dto.OrderDto;
import com.example.demo.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    Order createOrder(OrderDto orderDto);
}
