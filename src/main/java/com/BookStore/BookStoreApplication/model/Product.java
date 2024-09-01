package com.BookStore.BookStoreApplication.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @NotNull(message = "Name is required")
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    @NotNull(message = "Price is required")
    private double price;

    @Column(name = "stock")
    @NotNull(message = "Quantity is required")
    private int stock;

    @Column(name = "author")
    private String author;

    @Column(name = "category")
    private String category;

    @Column(name = "created_at",updatable = false)
    private Timestamp created_at;

    @Column(name = "updated_at")
    private Timestamp updated_at;



    @PrePersist
    protected void onCreate() {
        created_at = new Timestamp(System.currentTimeMillis());
        updated_at = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        updated_at = new Timestamp(System.currentTimeMillis());

    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}