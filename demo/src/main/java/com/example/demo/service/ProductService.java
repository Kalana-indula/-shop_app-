package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product createProduct(ProductDto productDto);
    Product updateProduct(Long id,Product product);
    List<Product> getProductByCategory(Long id);
}
