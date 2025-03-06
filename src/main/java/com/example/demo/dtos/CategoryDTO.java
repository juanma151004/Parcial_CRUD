// Package declaration
package com.example.demo.dtos;

// Importing Lombok annotations
import lombok.*;

// Lombok annotation to generate getter methods for all fields
@Getter
// Lombok annotation to generate setter methods for all fields
@Setter
// Lombok annotation to generate a no-argument constructor
@NoArgsConstructor
// Lombok annotation to generate an all-argument constructor
@AllArgsConstructor
public class CategoryDTO {
    // Private field to store the ID of the category
    private Long id;
    // Private field to store the name of the category
    private String name;
}
