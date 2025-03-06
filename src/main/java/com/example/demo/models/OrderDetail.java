package com.example.demo.models; // Package declaration

import jakarta.persistence.*; // Importing JPA annotations
import lombok.*; // Importing Lombok annotations

@Entity // Specifies that the class is an entity and is mapped to a database table
@Table(name = "order_details") // Specifies the table name in the database
@Getter // Lombok annotation to generate getter methods for all fields
@Setter // Lombok annotation to generate setter methods for all fields
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate an all-argument constructor
public class OrderDetail {
    @Id // Specifies the primary key of an entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies the primary key generation strategy
    private Long id; // Primary key field

    @ManyToOne // Specifies a many-to-one relationship with another entity
    @JoinColumn(name = "order_id", nullable = false) // Specifies the foreign key column and its properties
    private Order order; // Relationship with Order entity

    @ManyToOne // Specifies a many-to-one relationship with another entity
    @JoinColumn(name = "product_id", nullable = false) // Specifies the foreign key column and its properties
    private Product product; // Relationship with Product entity

    @Column(nullable = false) // Specifies the column properties
    private Integer quantity; // Quantity of the product in the order

    @Column(nullable = false) // Specifies the column properties
    private Double subtotal; // Subtotal calculated as quantity * price
}
