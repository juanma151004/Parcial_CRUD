package com.example.demo.dtos; // Defines the package where this class belongs

import lombok.*; // Imports all Lombok annotations
import java.time.LocalDate; // Imports the LocalDate class

@Getter // Lombok annotation to generate getter methods for all fields
@Setter // Lombok annotation to generate setter methods for all fields
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields as arguments
public class OrderDTO { // Defines the OrderDTO class
    private Long id; // Field to store the order ID
    private Long userId; // Field to store the user ID, representing a relationship with User
    private LocalDate orderDate; // Field to store the date of the order
}
