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

    private final OrderRepository orderRepository;
    private final UserRepository userRepository; // Se necesita para buscar el usuario

    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(order -> new OrderDTO(order.getId(), order.getUser().getId(), order.getOrderDate()))
                .collect(Collectors.toList());
    }

    public Optional<OrderDTO> getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(order -> new OrderDTO(order.getId(), order.getUser().getId(), order.getOrderDate()));
    }

    public Order createOrder(OrderDTO orderDTO) {
        // Buscar el usuario en la base de datos
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Crear la nueva orden con el usuario encontrado
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(orderDTO.getOrderDate());

        return orderRepository.save(order);
    }

    public Optional<Order> updateOrder(Long id, Long userId, Order updatedOrder) {
        if (id == null) {
            throw new IllegalArgumentException("Order ID must not be null");
        }
        if (userId == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
    
        return orderRepository.findById(id).map(existingOrder -> {
            Optional<User> userOpt = userRepository.findById(userId);
    
            if (userOpt.isPresent()) {
                existingOrder.setUser(userOpt.get());
                existingOrder.setOrderDate(updatedOrder.getOrderDate());
                return orderRepository.save(existingOrder);
            } else {
                throw new IllegalArgumentException("User not found with given ID");
            }
        });
    }

    public boolean deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

