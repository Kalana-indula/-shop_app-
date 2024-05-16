package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        Category category=categoryRepository.findById(productDto.getCategoryId()).orElse(null);

        if(category !=null){
            Product product=new Product();
            product.setName(productDto.getName());
            product.setQty(productDto.getQty());
            product.setPrice(productDto.getPrice());
            product.setCategory(category);

            return productRepository.save(product);
        }else{
            return null;
        }
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product existingProduct=productRepository.findById(id).orElse(null);

        if(existingProduct !=null){
            existingProduct.setName(product.getName());
            existingProduct.setQty(product.getQty());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setCategory(product.getCategory());

            return  productRepository.save(existingProduct);
        }else{
            return null;
        }
    }

    @Override
    public List<Product> getProductByCategory(Long id) {

        Category category=categoryRepository.findById(id).orElse(null);

        if(category !=null){
            return productRepository.findProductByCategory(category);
        }else{
            return null;
        }

    }
}
