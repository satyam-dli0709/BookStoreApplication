package com.BookStore.BookStoreApplication.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "Orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "orderId")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId ;

    @OneToOne
    @JoinColumn ( name = "user_id" , nullable = false)
    private User user ;
    // user_id is foreign keu inside orders table
    // name of join column is the custom foreign key name inside order table , and it is the primary key of user table
    // otherwise by default name of foreign key will not be custom
    @Column (name = "total_amount" , nullable = false)
    private double totalAmount ;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status" , nullable = false)
    private OrderStatus orderStatus;

   @Column (name = "created_at" , nullable = false)
    private Timestamp createdAt;

    @OneToMany(mappedBy = "orderItemsId", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderItems> OrderItems;
  // mapped by contains primary key of order item table

}

