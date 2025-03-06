package com.example.demo.repositories; // Defines the package for this repository interface

import com.example.demo.models.User; // Imports the User model
import org.springframework.data.jpa.repository.JpaRepository; // Imports JpaRepository for CRUD operations
import org.springframework.stereotype.Repository; // Imports Repository annotation

import java.util.Optional; // Imports Optional for handling null values

@Repository // Indicates that this interface is a Spring Data repository
public interface UserRepository extends JpaRepository<User, Long> { // Extends JpaRepository to provide CRUD operations for User entities with Long as ID type
    Optional<User> findByEmail(String email); // Declares a method to find a User by their email, returning an Optional to handle the possibility of no result
}
