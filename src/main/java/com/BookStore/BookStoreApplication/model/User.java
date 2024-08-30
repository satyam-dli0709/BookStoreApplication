package com.BookStore.BookStoreApplication.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9]{5,15}$", message = "username must be alphanumeric and between 5 to 15 characters long")
    private String username;

    @Column(unique = true, nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid email")
    private String email;

    @Column(nullable = false)
    @Pattern(regexp = "^[A-Za-z0-9]*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*$", message = "Password must contain at least one special character")
    private String password;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;



}