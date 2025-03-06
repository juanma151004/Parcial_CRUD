package com.example.demo.models; // Package declaration

import jakarta.persistence.*; // Import JPA annotations
import lombok.*; // Import Lombok annotations
import java.util.List; // Import List class

@Entity // Specifies that the class is an entity and is mapped to a database table
@Table(name = "products") // Specifies the table name in the database
@Getter // Lombok annotation to generate getter methods
@Setter // Lombok annotation to generate setter methods
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate an all-argument constructor
public class Product {
    @Id // Specifies the primary key of an entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies the primary key generation strategy
    private Long id; // Unique identifier for the product

    @Column(nullable = false) // Specifies the column mapping and that the column cannot be null
    private String name; // Name of the product

    @Column(nullable = false) // Specifies the column mapping and that the column cannot be null
    private Double price; // Price of the product

    @Column(nullable = false) // Specifies the column mapping and that the column cannot be null
    private Integer stock; // Stock quantity of the product

    @ManyToOne // Specifies a many-to-one relationship
    @JoinColumn(name = "category_id", nullable = false) // Specifies the foreign key column and that it cannot be null
    private Category category; // Relationship with Category entity

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true) // Specifies a one-to-many relationship with cascade and orphan removal
    private List<OrderDetail> details; // Relationship with OrderDetail entity
}
