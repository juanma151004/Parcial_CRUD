package com.example.demo.repositories; // Define the package for the repository

import com.example.demo.models.Order; // Import the Order model

import org.springframework.data.jpa.repository.JpaRepository; // Import JpaRepository from Spring Data JPA
import org.springframework.stereotype.Repository; // Import Repository annotation from Spring

import java.util.List; // Import List from Java util package

@Repository // Indicate that this interface is a Spring repository
public interface OrderRepository extends JpaRepository<Order, Long> { // Define an interface that extends JpaRepository for Order entities with Long as ID type
    List<Order> findByUserId(Long userId); // Declare a method to find orders by user ID
}
