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

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

        public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getStock(), product.getCategory().getId()))
                .collect(Collectors.toList());
    }

    public Optional<ProductDTO> getProductById(Long id) {
        return productRepository.findById(id)
                .map(product -> new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getStock(), product.getCategory().getId()));
    }

    public Product createProduct(ProductDTO productDTO) {
        // Buscar la categoría en la base de datos antes de asignarla
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setCategory(category); // Asignar la categoría encontrada

        return productRepository.save(product);
    }

    public Optional<Product> updateProduct(Long id, ProductDTO updatedProductDTO) {
        return productRepository.findById(id).map(existingProduct -> {
            existingProduct.setName(updatedProductDTO.getName());
            existingProduct.setPrice(updatedProductDTO.getPrice());
            existingProduct.setStock(updatedProductDTO.getStock());
    
            if (updatedProductDTO.getCategoryId() != null) {
                Category category = categoryRepository.findById(updatedProductDTO.getCategoryId())
                        .orElseThrow(() -> new RuntimeException("Category not found"));
                existingProduct.setCategory(category);
            }
    
            Product savedProduct = productRepository.save(existingProduct);
            System.out.println("Producto actualizado: " + savedProduct); // 🔥 Imprimir resultado
            return savedProduct;
        });
    }

    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}




