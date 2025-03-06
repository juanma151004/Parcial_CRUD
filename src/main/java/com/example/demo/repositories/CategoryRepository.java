package com.example.demo.repositories; // Defines the package for this repository interface

import com.example.demo.models.Category; // Imports the Category model

import org.springframework.data.jpa.repository.JpaRepository; // Imports JpaRepository from Spring Data JPA
import org.springframework.stereotype.Repository; // Imports the Repository annotation from Spring Framework

@Repository // Indicates that this interface is a Spring Data repository
public interface CategoryRepository extends JpaRepository<Category, Long> { // Extends JpaRepository to provide CRUD operations for Category entities with Long as the ID type
}
