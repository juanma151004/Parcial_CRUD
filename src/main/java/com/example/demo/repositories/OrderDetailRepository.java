package com.example.demo.repositories; // Defines the package for this repository interface

import com.example.demo.models.OrderDetail; // Imports the OrderDetail model

import org.springframework.data.jpa.repository.JpaRepository; // Imports the JpaRepository interface from Spring Data JPA
import org.springframework.stereotype.Repository; // Imports the Repository annotation from Spring Framework

@Repository // Indicates that this interface is a Spring Data repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {  
    // Extends JpaRepository to provide CRUD operations for OrderDetail entities with Long as the ID type
}
