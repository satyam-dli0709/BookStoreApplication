package com.BookStore.BookStoreApplication.service;

import com.BookStore.BookStoreApplication.exception.ProductNotFoundException;
import com.BookStore.BookStoreApplication.model.Product;
import com.BookStore.BookStoreApplication.model.User;
import com.BookStore.BookStoreApplication.model.Wishlist;
import com.BookStore.BookStoreApplication.model.WishlistItems;
import com.BookStore.BookStoreApplication.repository.ProductRepository;
import com.BookStore.BookStoreApplication.repository.UserRepository;
import com.BookStore.BookStoreApplication.repository.WishlistItemsRepository;
import com.BookStore.BookStoreApplication.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistItemsServiceImpl implements WishlistItemService{
    @Autowired
    private WishlistItemsRepository wishlistItemsRepository;

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public Wishlist addProductToWishlist(long userId, long productId) {
        Wishlist wishlist = wishlistRepository.findByUserId(userId)
                .orElseGet(() -> new Wishlist());

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ProductNotFoundException("User not found"));
        wishlist.setUser(user);
        wishlist.setCreated_at(new Timestamp(System.currentTimeMillis()));
        wishlistRepository.save(wishlist);
        WishlistItems wishlistItem = new WishlistItems();
        wishlistItem.setProduct(product);
        wishlistItem.setWishlist(wishlist);
        wishlistItemsRepository.save(wishlistItem);

        wishlist.getWishlistItems().add(wishlistItem);
        wishlistRepository.save(wishlist);

        return wishlist;
    }

    @Override
    public void removeWishlistItem(long productId) {
        wishlistItemsRepository.deleteById(productId);
    }

    public List<WishlistItems> findAllWishlistItems() {
        return wishlistItemsRepository.findAll();
    }


}
