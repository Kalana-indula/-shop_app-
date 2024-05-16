package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts(){
        try{
            return ResponseEntity.status(HttpStatus.FOUND).body(productService.getAllProducts());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @PostMapping("/products")
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productDto));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
        }
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id){
        Product product= productService.getProductById(id);

        if(product !=null){
            return ResponseEntity.status(HttpStatus.FOUND).body(product);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Product For Corresponding ID");
        }
    }

    @GetMapping("/categories/{id}/products")
    public ResponseEntity<?> getProductsByCategory(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.FOUND).body(productService.getProductByCategory(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
