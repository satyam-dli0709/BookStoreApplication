package com.BookStore.BookStoreApplication.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "Admins")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long adminId ;

    @Column (unique = true , nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9]{5,15}$", message = "username must be alphanumeric and between 5 to 15 characters long")
    private String adminName ;

    @Column (unique = true , nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid email")
    private String email;

    @Column (nullable = false)
    @Size(min = 6 , max = 15 , message = "password must be between 8 to 15 characters long")
    @Pattern(regexp = "^[A-Za-z0-9]*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*$", message = "Password must contain at least one special character")
    private String password ;

    @Column (name = "created_at" , nullable = false)
    private Timestamp createdAt;

}

