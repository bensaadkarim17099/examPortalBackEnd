package com.exam.controller;

import com.exam.model.exam.Category;
import com.exam.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //add category
    @PostMapping("/")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){

        return ResponseEntity.ok( this.categoryService.addCategory(category));
    }

    // Get Category
    @GetMapping("/{catId}")
    public Category getCategoryById(@PathVariable("catId") Long catId){
        return this.categoryService.getCategory(catId);
    }

    // get all categories
    @GetMapping("/")
    public ResponseEntity<?> getAllCategories(){

        return  ResponseEntity.ok(this.categoryService.getCategory());
    }

    //update category
    @PutMapping("/")
    public Category updateCategory(@RequestBody Category category){
        return  this.categoryService.updateCategory(category);
    }

    //delete category
    @DeleteMapping("/{catId}")
    public void deleteCategory(@PathVariable("catId") Long catId){
        this.categoryService.deleteCategory(catId);
    }


}







