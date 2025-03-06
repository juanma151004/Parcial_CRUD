package com.example.demo.dtos; // Defines the package for this class

import lombok.*; // Imports all Lombok annotations

@Getter // Lombok annotation to generate getter methods for all fields
@Setter // Lombok annotation to generate setter methods for all fields
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields as parameters
public class UserDTO {
    private Long id; // Field to store the user's ID
    private String name; // Field to store the user's name
    private String email; // Field to store the user's email
}
