package com.example.demo.controllers;

// Import necessary classes and annotations
import com.example.demo.dtos.CategoryDTO;
import com.example.demo.models.Category;
import com.example.demo.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Indicate that this class is a REST controller
@RestController
// Map HTTP requests to /categories to this controller
@RequestMapping("/categories")
public class CategoryController {

    // Declare a final variable for the CategoryService
    private final CategoryService categoryService;

    // Constructor to inject the CategoryService dependency
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Handle GET requests to /categories
    @GetMapping
    // Method to get all categories
    public List<CategoryDTO> getAllCategories() {
        // Call the service to get all categories and return the result
        return categoryService.getAllCategories();
    }

    // Handle GET requests to /categories/{id}
    @GetMapping("/{id}")
    // Method to get a category by its ID
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        // Call the service to get the category by ID
        Optional<CategoryDTO> category = categoryService.getCategoryById(id);
        // If the category is found, return it with a 200 OK status, otherwise return a 404 Not Found status
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Handle POST requests to /categories
    @PostMapping
    // Method to create a new category
    public Category createCategory(@RequestBody Category category) {
        // Call the service to create a new category and return the created category
        return categoryService.createCategory(category);
    }

    // Handle PUT requests to /categories/{id}
    @PutMapping("/{id}")
    // Method to update an existing category by its ID
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
        // Call the service to update the category
        return categoryService.updateCategory(id, updatedCategory)
                // If the update is successful, return the updated category with a 200 OK status
                .map(ResponseEntity::ok)
                // If the category is not found, return a 404 Not Found status
                .orElse(ResponseEntity.notFound().build());
    }

    // Handle DELETE requests to /categories/{id}
    @DeleteMapping("/{id}")
    // Method to delete a category by its ID
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        // Call the service to delete the category
        return categoryService.deleteCategory(id) 
                // If the deletion is successful, return a 200 OK status
                ? ResponseEntity.ok().build() 
                // If the category is not found, return a 404 Not Found status
                : ResponseEntity.notFound().build();
    }
}




