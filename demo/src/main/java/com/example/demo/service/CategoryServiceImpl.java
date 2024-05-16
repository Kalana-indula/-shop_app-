package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Category existingCategory=categoryRepository.findById(id).orElse(null);

        if(existingCategory !=null){
            existingCategory.setName(category.getName());
            return categoryRepository.save(existingCategory);
        }else{
            return null;
        }
    }
}
