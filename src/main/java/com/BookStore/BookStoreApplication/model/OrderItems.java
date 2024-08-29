package com.BookStore.BookStoreApplication.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "OrderItems")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "orderItemsId")

public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderItemsId ;

    @ManyToOne
    @JoinColumn(name = "order_id" , nullable = false)
    private Order order ;
    @Column (name = "quantity" , nullable = false)
    private int quantity ;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "id" , nullable = false)
    private Product product;

    @Column(name = "price" , nullable = false)
    private double price ;
}