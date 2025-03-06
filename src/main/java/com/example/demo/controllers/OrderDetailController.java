package com.example.demo.controllers;

import com.example.demo.dtos.OrderDetailDTO;
import com.example.demo.models.OrderDetail;
import com.example.demo.services.OrderDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order-details")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping
    public List<OrderDetailDTO> getAllOrderDetails() {
        return orderDetailService.getAllOrderDetails();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailDTO> getOrderDetailById(@PathVariable Long id) {
        Optional<OrderDetailDTO> detail = orderDetailService.getOrderDetailById(id);
        return detail.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createOrderDetail(@RequestBody OrderDetailDTO orderDetailDTO) {
        orderDetailService.createOrderDetail(orderDetailDTO);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrderDetail(
            @PathVariable Long id,
            @RequestBody OrderDetailDTO orderDetailDTO) {
    
        Optional<OrderDetail> updated = orderDetailService.updateOrderDetail(
                id, orderDetailDTO.getOrderId(), orderDetailDTO.getProductId(), orderDetailDTO.getQuantity());
    
        if (updated.isPresent()) {
            return ResponseEntity.ok("OK");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable Long id) {
        return orderDetailService.deleteOrderDetail(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}


