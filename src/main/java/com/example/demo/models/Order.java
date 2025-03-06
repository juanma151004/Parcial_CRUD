package com.example.demo.models; // Package declaration

import jakarta.persistence.*; // Import JPA annotations
import lombok.*; // Import Lombok annotations
import java.time.LocalDate; // Import LocalDate class
import java.util.List; // Import List interface

@Entity // Marks this class as a JPA entity
@Table(name = "orders") // Maps this entity to the "orders" table
@Getter // Lombok annotation to generate getter methods
@Setter // Lombok annotation to generate setter methods
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate an all-argument constructor
public class Order {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies the primary key generation strategy
    private Long id; // Unique identifier for each order

    @Column(nullable = false) // Maps this field to a column and specifies it cannot be null
    private LocalDate orderDate; // Date when the order was placed

    @ManyToOne // Specifies a many-to-one relationship with the User entity
    @JoinColumn(name = "user_id", nullable = false) // Maps this relationship to the "user_id" column and specifies it cannot be null
    private User user; // User who placed the order

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true) // Specifies a one-to-many relationship with OrderDetail entity
    private List<OrderDetail> details; // List of order details associated with this order

    // Constructor to initialize user and orderDate fields
    public Order(User user, LocalDate orderDate) {
        this.user = user;
        this.orderDate = orderDate;
    }
}

