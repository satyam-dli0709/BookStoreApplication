package com.BookStore.BookStoreApplication.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "wishlist")
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "wishlistId")
public class Wishlist {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "wishlistId")
    private long wishlistId;

    @Column(name = "created_at")
    private Timestamp created_at;

    @OneToOne
    @JoinColumn(name = "userId")
    @NotNull
    private User user;

    @OneToMany(mappedBy = "wishlist", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<WishlistItems> wishlistItems;

}
