package com.example.demo.controllers;

// Import necessary classes and annotations
import com.example.demo.dtos.OrderDTO;
import com.example.demo.models.Order;
import com.example.demo.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Indicate that this class is a REST controller
@RestController
// Map HTTP requests to /orders to this controller
@RequestMapping("/orders")
public class OrderController {

    // Declare a final variable for the OrderService
    private final OrderService orderService;

    // Constructor to inject the OrderService dependency
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Handle GET requests to /orders to retrieve all orders
    @GetMapping
    public List<OrderDTO> getAllOrders() {
        // Call the service to get all orders and return them
        return orderService.getAllOrders();
    }

    // Handle GET requests to /orders/{id} to retrieve a specific order by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        // Call the service to get the order by ID
        Optional<OrderDTO> order = orderService.getOrderById(id);
        // If the order is found, return it with a 200 OK status, otherwise return 404 Not Found
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Handle POST requests to /orders to create a new order
    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderDTO orderDTO) {
        // Call the service to create a new order
        orderService.createOrder(orderDTO);
        // Print "OK" to the console
        System.out.println("OK");
        // Return a response with "OK" and a 200 OK status
        return ResponseEntity.ok("OK");
    }

    // Handle PUT requests to /orders/{id} to update an existing order
    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        // Check if the user ID in the orderDTO is null
        if (orderDTO.getUserId() == null) {
            // If user ID is null, return a 400 Bad Request response with an error message
            return ResponseEntity.badRequest().body("User ID cannot be null");
        }
    
        // Call the service to update the order
        Optional<Order> updated = orderService.updateOrder(id, orderDTO.getUserId(), new Order(null, orderDTO.getOrderDate()));
    
        // If the order was updated, return a response with "OK" and a 200 OK status
        if (updated.isPresent()) {
            return ResponseEntity.ok("OK");
        } else {
            // If the order was not found, return a 404 Not Found response
            return ResponseEntity.notFound().build();
        }
    }

    // Handle DELETE requests to /orders/{id} to delete an order by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        // Call the service to delete the order by ID
        return orderService.deleteOrder(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}

