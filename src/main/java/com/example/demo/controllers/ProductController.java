package com.example.demo.controllers; // Declares the package for the class

import com.example.demo.dtos.ProductDTO; // Imports the ProductDTO class
import com.example.demo.models.Product; // Imports the Product class
import com.example.demo.services.ProductService; // Imports the ProductService class
import org.springframework.http.ResponseEntity; // Imports the ResponseEntity class from Spring
import org.springframework.web.bind.annotation.*; // Imports various annotations from Spring Web

import java.util.List; // Imports the List class from the Java standard library
import java.util.Optional; // Imports the Optional class from the Java standard library

@RestController // Marks this class as a REST controller
@RequestMapping("/products") // Maps HTTP requests to /products to this controller
public class ProductController {

    private final ProductService productService; // Declares a final variable for the ProductService

    public ProductController(ProductService productService) { // Constructor to inject the ProductService dependency
        this.productService = productService; // Assigns the injected ProductService to the class variable
    }

    @GetMapping // Maps HTTP GET requests to this method
    public List<ProductDTO> getAllProducts() { // Method to get all products
        return productService.getAllProducts(); // Calls the service to get all products and returns the list
    }

    @GetMapping("/{id}") // Maps HTTP GET requests with an ID parameter to this method
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) { // Method to get a product by ID
        Optional<ProductDTO> product = productService.getProductById(id); // Calls the service to get the product by ID
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); // Returns the product if found, otherwise returns a 404 response
    }

    @PostMapping // Maps HTTP POST requests to this method
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO) { // Method to create a new product
        Product product = productService.createProduct(productDTO); // Calls the service to create a new product
        System.out.println("OK"); // Prints "OK" to the console
        return ResponseEntity.ok(product); // Returns the created product with a 200 OK response
    }

    @PutMapping("/{id}") // Maps HTTP PUT requests with an ID parameter to this method
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) { // Method to update an existing product
        return productService.updateProduct(id, productDTO) // Calls the service to update the product
                .map(ResponseEntity::ok) // Returns the updated product with a 200 OK response if found
                .orElseGet(() -> ResponseEntity.notFound().build()); // Returns a 404 response if the product is not found
    }

    @DeleteMapping("/{id}") // Maps HTTP DELETE requests with an ID parameter to this method
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) { // Method to delete a product by ID
        return productService.deleteProduct(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build(); // Calls the service to delete the product and returns a 200 OK response if successful, otherwise a 404 response
    }
}