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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistItemsServiceImpl implements WishlistItemService {
    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private WishlistItemsRepository wishlistItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//   Wishlist wishlist;

    @Override
    public Wishlist AddproducttoWishlist(Long userId, Long id) {
        Wishlist wishlist = wishlistRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Wishlist newWishlist = new Wishlist();
                    newWishlist.setCreated_at(Timestamp.valueOf(LocalDateTime.now()));
                    newWishlist.setWishlistItems(new ArrayList<>()); // Initialize the wishlistItems list
                    return newWishlist;
                });

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ProductNotFoundException("User not found"));

        wishlist.setUser(user);
        wishlist = wishlistRepository.save(wishlist);
        boolean productExists = false;
        String targetId = String.valueOf(id);

        for (WishlistItems item : wishlist.getWishlistItems()) {
            if (String.valueOf(item.getProduct().getId()).contentEquals(targetId)) {
                productExists = true;
                break;
            }
        }

        if (productExists) {
            throw new ProductNotFoundException("Product already in wishlist");
        }

        WishlistItems wishlistItem = new WishlistItems();
        wishlistItem.setProduct(product);
        wishlistItem.setWishlist(wishlist);
        wishlistItemRepository.save(wishlistItem);
        wishlist.getWishlistItems().add(wishlistItem);
        wishlistRepository.save(wishlist);

        return wishlist;
    }



//    @Autowired
//    private WishlistItemsRepository wishlistItemsRepository;


    @Override
    public void removeWishlistItem(long wishlistItemId) {
        wishlistItemRepository.deleteById(wishlistItemId);
    }

    public List<WishlistItems> findAllWishlistItems() {
        return wishlistItemRepository.findAll();
    }

    @Override
    public Optional<Wishlist> findWishlistItemsByUserId(Long userId) {
        return wishlistRepository.findByUserId(userId);
    }

    @Override
    public WishlistItems findWishlistItemById(Long wishlistItemId) {
        return wishlistItemRepository.findByWishlistItemId(wishlistItemId);
    }
}

