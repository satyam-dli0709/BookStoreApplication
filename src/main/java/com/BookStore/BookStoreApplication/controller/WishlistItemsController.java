package com.BookStore.BookStoreApplication.controller;

import com.BookStore.BookStoreApplication.model.Wishlist;
import com.BookStore.BookStoreApplication.model.WishlistItems;
import com.BookStore.BookStoreApplication.service.WishlistItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookstore_user")
public class WishlistItemsController {
    @Autowired
    private WishlistItemService wishlistItemsService;


    @PostMapping("/add_wish_list")
    public ResponseEntity<Object> addItemToWishlist(@RequestParam Long userId, @RequestParam Long id) {
        Wishlist wishlist = wishlistItemsService.AddproducttoWishlist(userId, id);
        return new ResponseEntity<>("added wishlist successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/remove_wishlist_item")
    public ResponseEntity<String> removeWishlistItem(@RequestParam Long wishlistItemId) {
        wishlistItemsService.removeWishlistItem(wishlistItemId);
        return new ResponseEntity<>("Wishlist item removed successfully", HttpStatus.OK);
    }

    @GetMapping("/get_wishlist_items")
    public ResponseEntity<List<WishlistItems>> findAllWishlistItems() {
         return new ResponseEntity<>(wishlistItemsService.findAllWishlistItems(), HttpStatus.OK);
    }

    @GetMapping("/get_wishlist_items/{userId}")
    public ResponseEntity<Optional<Wishlist>> getWishlistItemsByUserId(@PathVariable("userId") Long userId) {
        Optional<Wishlist> wishlist = wishlistItemsService.findWishlistItemsByUserId(userId);
        if (wishlist.isPresent()) {
            return ResponseEntity.ok(wishlist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
