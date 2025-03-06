package com.example.demo.controllers;

import com.example.demo.dtos.OrderDTO;
import com.example.demo.models.Order;
import com.example.demo.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        Optional<OrderDTO> order = orderService.getOrderById(id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderDTO orderDTO) {
        orderService.createOrder(orderDTO);
        System.out.println("OK"); // Imprime "OK" en la consola
        return ResponseEntity.ok("OK"); // Responde con "OK"
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        if (orderDTO.getUserId() == null) {
            return ResponseEntity.badRequest().body("User ID cannot be null");
        }
    
        Optional<Order> updated = orderService.updateOrder(id, orderDTO.getUserId(), new Order(null, orderDTO.getOrderDate())
        );
    
        if (updated.isPresent()) {
            return ResponseEntity.ok("OK");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        return orderService.deleteOrder(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}

