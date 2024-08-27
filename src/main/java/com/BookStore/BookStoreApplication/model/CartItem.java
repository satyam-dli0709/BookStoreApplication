package com.BookStore.BookStoreApplication.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cartitem")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "cartItemId")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cartItemId;

    @NotNull
    private int quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartId")
    private Cart cart;

    @OneToOne(mappedBy = "cartItem", cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Product products;


}
