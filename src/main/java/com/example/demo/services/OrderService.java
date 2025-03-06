package com.example.demo.services;

import com.example.demo.dtos.OrderDTO;
import com.example.demo.models.Order;
import com.example.demo.models.User;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository; // Repository for Order entities
    private final UserRepository userRepository; // Repository for User entities

    // Constructor to initialize OrderRepository and UserRepository
    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    // Method to get all orders and convert them to OrderDTO
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream() // Fetch all orders
                .map(order -> new OrderDTO(order.getId(), order.getUser().getId(), order.getOrderDate())) // Convert each order to OrderDTO
                .collect(Collectors.toList()); // Collect the results into a list
    }

    // Method to get a specific order by its ID and convert it to OrderDTO
    public Optional<OrderDTO> getOrderById(Long id) {
        return orderRepository.findById(id) // Find order by ID
                .map(order -> new OrderDTO(order.getId(), order.getUser().getId(), order.getOrderDate())); // Convert order to OrderDTO if found
    }

    // Method to create a new order from OrderDTO
    public Order createOrder(OrderDTO orderDTO) {
        User user = userRepository.findById(orderDTO.getUserId()) // Find user by ID
                .orElseThrow(() -> new RuntimeException("User not found")); // Throw exception if user not found

        Order order = new Order(); // Create new Order entity
        order.setUser(user); // Set user for the order
        order.setOrderDate(orderDTO.getOrderDate()); // Set order date

        return orderRepository.save(order); // Save the order and return it
    }

    // Method to update an existing order
    public Optional<Order> updateOrder(Long id, Long userId, Order updatedOrder) {
        if (id == null) {
            throw new IllegalArgumentException("Order ID must not be null"); // Validate order ID
        }
        if (userId == null) {
            throw new IllegalArgumentException("User ID must not be null"); // Validate user ID
        }

        return orderRepository.findById(id).map(existingOrder -> { // Find existing order by ID
            Optional<User> userOpt = userRepository.findById(userId); // Find user by ID

            if (userOpt.isPresent()) {
                existingOrder.setUser(userOpt.get()); // Update user for the order
                existingOrder.setOrderDate(updatedOrder.getOrderDate()); // Update order date
                return orderRepository.save(existingOrder); // Save the updated order
            } else {
                throw new IllegalArgumentException("User not found with given ID"); // Throw exception if user not found
            }
        });
    }

    // Method to delete an order by its ID
    public boolean deleteOrder(Long id) {
        if (orderRepository.existsById(id)) { // Check if order exists by ID
            orderRepository.deleteById(id); // Delete order by ID
            return true; // Return true if deletion was successful
        }
        return false; // Return false if order does not exist
    }
}

