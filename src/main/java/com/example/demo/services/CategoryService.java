package com.example.demo.services;

import com.example.demo.dtos.CategoryDTO;
import com.example.demo.models.Category;
import com.example.demo.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // Marks this class as a Spring service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // Constructor to inject the CategoryRepository dependency
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Method to get all categories and map them to CategoryDTO
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream() // Fetch all categories from the repository
                .map(category -> new CategoryDTO(category.getId(), category.getName())) // Map each Category to CategoryDTO
                .collect(Collectors.toList()); // Collect the results into a List
    }

    // Method to get a category by its ID and map it to CategoryDTO
    public Optional<CategoryDTO> getCategoryById(Long id) {
        return categoryRepository.findById(id) // Find category by ID
                .map(category -> new CategoryDTO(category.getId(), category.getName())); // Map the found Category to CategoryDTO
    }

    // Method to create a new category
    public Category createCategory(Category category) {
        return categoryRepository.save(category); // Save the new category to the repository
    }

    // Method to update an existing category
    public Optional<Category> updateCategory(Long id, Category updatedCategory) {
        return categoryRepository.findById(id).map(existingCategory -> { // Find the category by ID
            existingCategory.setName(updatedCategory.getName()); // Update the category name
            return categoryRepository.save(existingCategory); // Save the updated category to the repository
        });
    }

    // Method to delete a category by its ID
    public boolean deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) { // Check if the category exists by ID
            categoryRepository.deleteById(id); // Delete the category by ID
            return true; // Return true if the category was deleted
        }
        return false; // Return false if the category was not found
    }
}



