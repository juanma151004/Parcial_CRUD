package com.example.demo; // Defines the package name for the class

import org.springframework.boot.SpringApplication; // Imports the SpringApplication class from Spring Boot
import org.springframework.boot.autoconfigure.SpringBootApplication; // Imports the SpringBootApplication annotation from Spring Boot

@SpringBootApplication // Indicates that this is a Spring Boot application
public class Demo2Application {

	// Main method to run the Spring Boot application
	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args); // Runs the Spring Boot application
	}

}
