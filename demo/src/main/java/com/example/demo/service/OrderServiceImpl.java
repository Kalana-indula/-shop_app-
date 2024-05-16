package com.example.demo.service;

import com.example.demo.dto.OrderDto;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order createOrder(OrderDto orderDto) {

        Order order=new Order();

        List<Long> products=orderDto.getProducts();

        Set<Product> productSet=new HashSet<>();

        order.setTotal(0.0);

        for(Long productId:products){
            Product product=productRepository.findById(productId).orElse(null);

            if(product !=null){
                order.setTotal(order.getTotal()+product.getPrice());
                productSet.add(product);
            }
        }
        Double tax=order.getTotal()*15/100;

        order.setTax(tax);
        order.setOrderTime(LocalDateTime.now());
        order.setProducts(productSet);

        return orderRepository.save(order);
    }
}
