package com.example.demo.repositories; // Defines the package for the repository

import com.example.demo.models.Product; // Imports the Product model

import org.springframework.data.jpa.repository.JpaRepository; // Imports JpaRepository for CRUD operations
import org.springframework.stereotype.Repository; // Imports Repository annotation

@Repository // Indicates that this interface is a Spring repository
public interface ProductRepository extends JpaRepository<Product, Long> { // Extends JpaRepository to provide CRUD operations for Product entities with Long as the ID type
}
