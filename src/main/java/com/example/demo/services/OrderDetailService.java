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

    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderDetailService(OrderDetailRepository orderDetailRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public List<OrderDetailDTO> getAllOrderDetails() {
        return orderDetailRepository.findAll().stream()
                .map(detail -> new OrderDetailDTO(detail.getId(), detail.getOrder().getId(), detail.getProduct().getId(), detail.getQuantity()))
                .collect(Collectors.toList());
    }

    public Optional<OrderDetailDTO> getOrderDetailById(Long id) {
        return orderDetailRepository.findById(id)
                .map(detail -> new OrderDetailDTO(detail.getId(), detail.getOrder().getId(), detail.getProduct().getId(), detail.getQuantity()));
    }

    public OrderDetail createOrderDetail(OrderDetailDTO orderDetailDTO) {
        // 🔹 Buscar la orden por ID
        Order order = orderRepository.findById(orderDetailDTO.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderDetailDTO.getOrderId()));

        // 🔹 Buscar el producto por ID
        Product product = productRepository.findById(orderDetailDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + orderDetailDTO.getProductId()));

        // 🔹 Calcular subtotal (cantidad * precio del producto)
        double subtotal = orderDetailDTO.getQuantity() * product.getPrice();

        // 🔹 Crear el OrderDetail con los valores correctos
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        orderDetail.setQuantity(orderDetailDTO.getQuantity());
        orderDetail.setSubtotal(subtotal); // 🔹 ¡Asegurar que el subtotal se guarde!

        return orderDetailRepository.save(orderDetail);
    }

    public Optional<OrderDetail> updateOrderDetail(Long id, Long orderId, Long productId, int quantity) {
        return orderDetailRepository.findById(id).map(existingDetail -> {
            // 🔹 Buscar el pedido y el producto por ID
            Optional<Order> orderOpt = orderRepository.findById(orderId);
            Optional<Product> productOpt = productRepository.findById(productId);
    
            if (orderOpt.isPresent() && productOpt.isPresent()) {
                // 🔹 Asignar valores actualizados
                existingDetail.setOrder(orderOpt.get());
                existingDetail.setProduct(productOpt.get());
                existingDetail.setQuantity(quantity);
                existingDetail.setSubtotal(quantity * productOpt.get().getPrice()); // 🔹 Recalcular subtotal
    
                return orderDetailRepository.save(existingDetail);
            }
            return null;
        });
    }
    

    public boolean deleteOrderDetail(Long id) {
        if (orderDetailRepository.existsById(id)) {
            orderDetailRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

