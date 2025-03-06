package com.example.demo.controllers; // Defines the package for this class

// Import necessary classes and annotations
import com.example.demo.dtos.OrderDetailDTO;
import com.example.demo.models.OrderDetail;
import com.example.demo.services.OrderDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Indicate that this class is a REST controller
@RestController
// Map HTTP requests to /order-details to this controller
@RequestMapping("/order-details")
public class OrderDetailController {

    // Declare a final variable for the OrderDetailService
    private final OrderDetailService orderDetailService;

    // Constructor to inject the OrderDetailService dependency
    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    // Handle GET requests to /order-details to retrieve all order details
    @GetMapping
    public List<OrderDetailDTO> getAllOrderDetails() {
        // Calls the service method to retrieve all order details and returns them as a list of OrderDetailDTO
        return orderDetailService.getAllOrderDetails();
    }

    // Handle GET requests to /order-details/{id} to retrieve a specific order detail by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailDTO> getOrderDetailById(@PathVariable Long id) {
        // Calls the service method to retrieve an order detail by its ID
        Optional<OrderDetailDTO> detail = orderDetailService.getOrderDetailById(id);
        // If the order detail is found, return it with an HTTP 200 status
        // If not found, return an HTTP 404 status
        return detail.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Handle POST requests to /order-details to create a new order detail
    @PostMapping
    public ResponseEntity<String> createOrderDetail(@RequestBody OrderDetailDTO orderDetailDTO) {
        // Calls the service method to create a new order detail using the provided OrderDetailDTO
        orderDetailService.createOrderDetail(orderDetailDTO);
        // Returns an HTTP 200 status with a message "OK"
        return ResponseEntity.ok("OK");
    }

    // Handle PUT requests to /order-details/{id} to update an existing order detail
    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrderDetail(
            @PathVariable Long id,
            @RequestBody OrderDetailDTO orderDetailDTO) {
    
        // Calls the service method to update an existing order detail with the provided ID and details from OrderDetailDTO
        Optional<OrderDetail> updated = orderDetailService.updateOrderDetail(
                id, orderDetailDTO.getOrderId(), orderDetailDTO.getProductId(), orderDetailDTO.getQuantity());
    
        // If the update is successful, return an HTTP 200 status with a message "OK"
        // If the order detail with the provided ID is not found, return an HTTP 404 status
        if (updated.isPresent()) {
            return ResponseEntity.ok("OK");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Handle DELETE requests to /order-details/{id} to delete an order detail by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable Long id) {
        // Calls the service method to delete an order detail by its ID
        // If the deletion is successful, return an HTTP 200 status
        // If the order detail with the provided ID is not found, return an HTTP 404 status
        return orderDetailService.deleteOrderDetail(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}