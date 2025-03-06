package com.example.demo.controllers; // Defines the package for the class

import com.example.demo.dtos.UserDTO; // Imports UserDTO class
import com.example.demo.models.User; // Imports User class
import com.example.demo.services.UserService; // Imports UserService class
import org.springframework.http.ResponseEntity; // Imports ResponseEntity class for HTTP responses
import org.springframework.web.bind.annotation.*; // Imports Spring annotations for REST controllers

import java.util.List; // Imports List class
import java.util.Optional; // Imports Optional class

@RestController // Marks this class as a REST controller
@RequestMapping("/users") // Maps HTTP requests to /users to this controller
public class UserController {

    private final UserService userService; // Declares a final UserService variable

    public UserController(UserService userService) { // Constructor to inject UserService dependency
        this.userService = userService;
    }

    @GetMapping // Maps HTTP GET requests to /users to this method
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers(); // Calls the service to get all users and returns the list
    }

    @GetMapping("/{id}") // Maps HTTP GET requests to /users/{id} to this method
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        Optional<UserDTO> user = userService.getUserById(id); // Calls the service to get a user by ID
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); // Returns the user if found, otherwise returns 404
    }

    @PostMapping // Maps HTTP POST requests to /users to this method
    public User createUser(@RequestBody User user) {
        return userService.createUser(user); // Calls the service to create a new user and returns the created user
    }

    @PutMapping("/{id}") // Maps HTTP PUT requests to /users/{id} to this method
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser) // Calls the service to update a user by ID
                .map(ResponseEntity::ok) // Returns the updated user if found
                .orElse(ResponseEntity.notFound().build()); // Returns 404 if the user is not found
    }

    /**
     * Deletes a user by ID.
     * @param id The user ID.
     * @return ResponseEntity with status OK or Not Found.
     */
    @DeleteMapping("/{id}") // Maps HTTP DELETE requests to /users/{id} to this method
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build(); // Calls the service to delete a user by ID and returns appropriate response
    }
}
