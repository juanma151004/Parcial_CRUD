package com.example.demo.dtos; // Define the package for this class

import lombok.*; // Import all Lombok annotations

@Getter // Lombok annotation to generate getter methods for all fields
@Setter // Lombok annotation to generate setter methods for all fields
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields as arguments
public class OrderDetailDTO { // Define the OrderDetailDTO class
    private Long id; // Field to store the ID of the order detail
    private Long orderId; // Field to store the ID of the related order
    private Long productId; // Field to store the ID of the related product
    private int quantity; // Field to store the quantity of the product in the order
}
