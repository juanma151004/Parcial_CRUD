package com.example.demo.services;

import com.example.demo.dtos.ProductDTO;
import com.example.demo.models.Category;
import com.example.demo.models.Product;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    // Repositories for accessing product and category data
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    // Constructor to initialize repositories
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    // Method to get all products and convert them to ProductDTO
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getStock(), product.getCategory().getId()))
                .collect(Collectors.toList());
    }

    // Method to get a product by its ID and convert it to ProductDTO
    public Optional<ProductDTO> getProductById(Long id) {
        return productRepository.findById(id)
                .map(product -> new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getStock(), product.getCategory().getId()));
    }

    // Method to create a new product from ProductDTO
    public Product createProduct(ProductDTO productDTO) {
        // Find the category in the database before assigning it
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Create a new product and set its properties
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setCategory(category); // Assign the found category

        // Save the product to the repository and return it
        return productRepository.save(product);
    }

    // Method to update an existing product
    public Optional<Product> updateProduct(Long id, ProductDTO updatedProductDTO) {
        return productRepository.findById(id).map(existingProduct -> {
            // Update product properties
            existingProduct.setName(updatedProductDTO.getName());
            existingProduct.setPrice(updatedProductDTO.getPrice());
            existingProduct.setStock(updatedProductDTO.getStock());

            // Update category if provided
            if (updatedProductDTO.getCategoryId() != null) {
                Category category = categoryRepository.findById(updatedProductDTO.getCategoryId())
                        .orElseThrow(() -> new RuntimeException("Category not found"));
                existingProduct.setCategory(category);
            }

            // Save the updated product and print the result
            Product savedProduct = productRepository.save(existingProduct);
            System.out.println("Producto actualizado: " + savedProduct); // Print result
            return savedProduct;
        });
    }

    // Method to delete a product by its ID
    public boolean deleteProduct(Long id) {
        // Check if the product exists before deleting
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}




