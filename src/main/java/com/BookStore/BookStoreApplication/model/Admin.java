package com.BookStore.BookStoreApplication.model;
import jakarta.persistence.*;
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
    private String adminName ;

    @Column (unique = true , nullable = false)
    private String email;

    @Column (nullable = false)
    private String password ;

    @Column (name = "created_at" , nullable = false)
    private Timestamp createdAt;

}

