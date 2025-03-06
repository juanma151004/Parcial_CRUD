package com.example.demo.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    private Long id;
    private Long orderId;   // Relación con Order
    private Long productId; // Relación con Product
    private int quantity;
   
}
