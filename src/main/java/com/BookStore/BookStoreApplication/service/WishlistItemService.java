package com.BookStore.BookStoreApplication.service;

import com.BookStore.BookStoreApplication.model.CartItem;
import com.BookStore.BookStoreApplication.model.Wishlist;
import com.BookStore.BookStoreApplication.model.WishlistItems;

import java.util.List;
import java.util.Optional;

public interface WishlistItemService {
    void removeWishlistItem(long productId);

    List<WishlistItems> findAllWishlistItems();

    Wishlist addProductToWishlist(long userId, long productId);

    Wishlist findWishlistByUserId(long userId);
}
