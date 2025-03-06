package com.example.demo.services;

import com.example.demo.dtos.UserDTO;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    // Dependency injection of UserRepository
    private final UserRepository userRepository;

    // Constructor to initialize UserRepository
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves all users and maps them to DTOs.
     * @return List of UserDTO objects.
     */
    public List<UserDTO> getAllUsers() {
        // Fetch all users, map each user to UserDTO, and collect as a list
        return userRepository.findAll().stream()
                .map(user -> new UserDTO(user.getId(), user.getName(), user.getEmail()))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a single user by ID.
     * @param id The ID of the user.
     * @return Optional containing UserDTO if found.
     */
    public Optional<UserDTO> getUserById(Long id) {
        // Find user by ID and map to UserDTO if present
        return userRepository.findById(id)
                .map(user -> new UserDTO(user.getId(), user.getName(), user.getEmail()));
    }

    /**
     * Creates a new user and persists it in the database.
     * @param user The user entity.
     * @return The saved user.
     */
    public User createUser(User user) {
        // Save the new user entity to the database
        return userRepository.save(user);
    }

    /**
     * Updates an existing user if found.
     * @param id The ID of the user to update.
     * @param updatedUser The new user data.
     * @return Optional containing updated User entity.
     */
    public Optional<User> updateUser(Long id, User updatedUser) {
        // Find user by ID, update fields if present, and save the updated user
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
            return userRepository.save(existingUser);
        });
    }

    /**
     * Deletes a user by ID.
     * @param id The ID of the user to delete.
     * @return True if deleted, false if not found.
     */
    public boolean deleteUser(Long id) {
        // Check if user exists by ID, delete if present
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

