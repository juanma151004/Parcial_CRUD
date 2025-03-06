package com.example.demo.services;

import com.example.demo.dtos.OrderDetailDTO;
import com.example.demo.models.Order;
import com.example.demo.models.OrderDetail;
import com.example.demo.models.Product;
import com.example.demo.repositories.OrderDetailRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.ProductRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderDetailService {

    // Repositories for accessing order details, orders, and products
    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    // Constructor to initialize repositories
    public OrderDetailService(OrderDetailRepository orderDetailRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    // Method to get all order details and convert them to DTOs
    public List<OrderDetailDTO> getAllOrderDetails() {
        return orderDetailRepository.findAll().stream()
                .map(detail -> new OrderDetailDTO(detail.getId(), detail.getOrder().getId(), detail.getProduct().getId(), detail.getQuantity()))
                .collect(Collectors.toList());
    }

    // Method to get a specific order detail by its ID and convert it to a DTO
    public Optional<OrderDetailDTO> getOrderDetailById(Long id) {
        return orderDetailRepository.findById(id)
                .map(detail -> new OrderDetailDTO(detail.getId(), detail.getOrder().getId(), detail.getProduct().getId(), detail.getQuantity()));
    }

    // Method to create a new order detail from a DTO
    public OrderDetail createOrderDetail(OrderDetailDTO orderDetailDTO) {
        
        // Find the order by ID, throw an exception if not found
        Order order = orderRepository.findById(orderDetailDTO.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderDetailDTO.getOrderId()));

        // Find the product by ID, throw an exception if not found
        Product product = productRepository.findById(orderDetailDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + orderDetailDTO.getProductId()));

        // Calculate the subtotal for the order detail
        double subtotal = orderDetailDTO.getQuantity() * product.getPrice();

        // Create a new order detail and set its properties
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        orderDetail.setQuantity(orderDetailDTO.getQuantity());
        orderDetail.setSubtotal(subtotal); // Set the subtotal

        // Save the new order detail to the repository and return it
        return orderDetailRepository.save(orderDetail);
    }

    // Method to update an existing order detail
    public Optional<OrderDetail> updateOrderDetail(Long id, Long orderId, Long productId, int quantity) {
        return orderDetailRepository.findById(id).map(existingDetail -> {
           
            // Find the order and product by their IDs
            Optional<Order> orderOpt = orderRepository.findById(orderId);
            Optional<Product> productOpt = productRepository.findById(productId);
    
            // If both order and product are found, update the order detail
            if (orderOpt.isPresent() && productOpt.isPresent()) {
               
                existingDetail.setOrder(orderOpt.get());
                existingDetail.setProduct(productOpt.get());
                existingDetail.setQuantity(quantity);
                existingDetail.setSubtotal(quantity * productOpt.get().getPrice()); // Update the subtotal
    
                // Save the updated order detail to the repository and return it
                return orderDetailRepository.save(existingDetail);
            }
            return null; // Return null if order or product is not found
        });
    }
    
    // Method to delete an order detail by its ID
    public boolean deleteOrderDetail(Long id) {
        if (orderDetailRepository.existsById(id)) {
            orderDetailRepository.deleteById(id); // Delete the order detail if it exists
            return true; // Return true if deletion was successful
        }
        return false; // Return false if the order detail does not exist
    }
}

