package com.BookStore.BookStoreApplication.service;

import com.BookStore.BookStoreApplication.model.CartItem;
import com.BookStore.BookStoreApplication.model.Wishlist;
import com.BookStore.BookStoreApplication.model.WishlistItems;

import java.util.List;
import java.util.Optional;

public interface WishlistItemService {
    Wishlist AddproducttoWishlist(Long userId, Long id);
    void removeWishlistItem(long id);

    List<WishlistItems> findAllWishlistItems();

    Optional<Wishlist> findWishlistItemsByUserId(Long userId);

    WishlistItems findWishlistItemById(Long wishlistItemId);
}
