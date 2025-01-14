package com.BookStore.BookStoreApplication.repository;

import com.BookStore.BookStoreApplication.model.WishlistItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistItemsRepository extends JpaRepository<WishlistItems, Long> {

    @Query("SELECT w FROM WishlistItems w WHERE w.wishlistItemId = ?1")
    WishlistItems findByWishlistItemId(Long wishlistItemId);
}
