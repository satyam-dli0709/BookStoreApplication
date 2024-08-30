package com.BookStore.BookStoreApplication.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wishlist_items")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "wishlistItemId")
public class WishlistItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "wishlistItemId")
    private long wishlistItemId;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="wishlistId")
    private Wishlist wishlist;

    @Override
    public String toString() {
        return "WishlistItems{" +
                "wishlistItemId=" + wishlistItemId +
                '}';
    }

    @OneToOne(cascade = CascadeType.ALL)
    @NotNull
    @JoinColumn(name="id")
    private Product product;


}
