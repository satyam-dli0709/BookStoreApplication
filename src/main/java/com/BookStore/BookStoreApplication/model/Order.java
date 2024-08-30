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
    private long orderId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column (name = "total_amount" , nullable = false)
    private double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status" ,nullable = false)
    private OrderStatus orderStatus;

    @Column (name = "created_at" , nullable = false)
    private Timestamp createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItems> orderItems;

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ",user=" +(user != null ? user.getUserId() : null)+
                ", totalAmount=" + totalAmount +
                ", orderStatus=" + orderStatus +
                ", createdAt=" + createdAt +
                ", orderItems=" + orderItems +
                '}';
    }
}

