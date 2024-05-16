package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<?> getAllCategories(){

        List<Category> categoryList=categoryService.getAllCategories();

        if(categoryList !=null){
            return ResponseEntity.status(HttpStatus.FOUND).body(categoryList);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Categories ");
        }
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<?> findCategoryById(@PathVariable Long id){

        Category category= categoryService.findCategoryById(id);

        if(category !=null){
            return ResponseEntity.status(HttpStatus.FOUND).body(category);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Category Found For The Corresponding ID");
        }
    }

    @PostMapping("/categories")
    public ResponseEntity<?> createCategory(@RequestBody Category category){

        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(category));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("/categories/{id}/update")
    public ResponseEntity<?> updateCategory(@PathVariable Long id,@RequestBody Category category){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(categoryService.updateCategory(id,category));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(e.getMessage());
        }
    }
}
