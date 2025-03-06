package com.example.demo.models; // Package declaration

import jakarta.persistence.*; // Importing JPA annotations
import lombok.*; // Importing Lombok annotations
import java.util.List; // Importing List class

@Entity // Specifies that the class is an entity and is mapped to a database table
@Table(name = "users") // Specifies the table name in the database
@Getter // Lombok annotation to generate getter methods for all fields
@Setter // Lombok annotation to generate setter methods for all fields
@NoArgsConstructor(access = AccessLevel.PROTECTED) // Lombok annotation to generate a no-args constructor with protected access
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields as parameters
public class User {
    @Id // Specifies the primary key of an entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies the primary key generation strategy
    private Long id; // Unique identifier for the user

    @Column(nullable = false) // Specifies that the column is not nullable
    private String name; // Name of the user

    @Column(nullable = false, unique = true) // Specifies that the column is not nullable and must be unique
    private String email; // Email of the user

    @Column(nullable = false) // Specifies that the column is not nullable
    private String password; // Password of the user

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) // Specifies a one-to-many relationship with the Order entity
    private List<Order> orders; // List of orders associated with the user
}
