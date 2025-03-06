package com.example.demo.dtos; // Declares the package for this class

import lombok.*; // Imports all Lombok annotations

@Getter // Lombok annotation to generate getter methods for all fields
@Setter // Lombok annotation to generate setter methods for all fields
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields as arguments
public class ProductDTO { // Declares the ProductDTO class
    private Long id; // Field to store the product ID
    private String name; // Field to store the product name
    private double price; // Field to store the product price
    private Integer stock; // Field to store the product stock quantity
    private Long categoryId; // Field to store the category ID, representing a relationship with Category
}
