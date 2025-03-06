package com.example.demo.models; // Defines the package for the class

import jakarta.persistence.*; // Imports JPA annotations
import lombok.*; // Imports Lombok annotations
import java.util.List; // Imports List from java.util

@Entity // Specifies that the class is an entity and is mapped to a database table
@Table(name = "categories") // Specifies the table name in the database
@Getter // Lombok annotation to generate getter methods for all fields
@Setter // Lombok annotation to generate setter methods for all fields
@NoArgsConstructor(access = AccessLevel.PROTECTED) // Lombok annotation to generate a no-args constructor with protected access
@AllArgsConstructor // Lombok annotation to generate an all-args constructor
public class Category {
    @Id // Specifies the primary key of an entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies the generation strategy for the primary key
    private Long id; // The unique identifier for each category

    @Column(nullable = false, unique = true) // Specifies the column details in the database
    private String name; // The name of the category, which must be unique and not null

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true) // Specifies a one-to-many relationship with the Product entity
    private List<Product> products; // List of products associated with the category
}
