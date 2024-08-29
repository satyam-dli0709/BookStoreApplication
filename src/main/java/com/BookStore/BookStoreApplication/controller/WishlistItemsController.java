package com.BookStore.BookStoreApplication.controller;

import com.BookStore.BookStoreApplication.model.Wishlist;
import com.BookStore.BookStoreApplication.model.WishlistItems;
import com.BookStore.BookStoreApplication.service.WishlistItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookstore_user")
public class WishlistItemsController {
    @Autowired
    private WishlistItemService wishlistItemsService;


    @PostMapping("/add_wish_list")
    public ResponseEntity<Object> addItemToWishlist(@RequestParam Long userId, @RequestParam Long productId) {
        Wishlist wishlist = wishlistItemsService.addProductToWishlist(userId, productId);
        return new ResponseEntity<>("added wishlist successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/remove_wishlist_item")
    public ResponseEntity<String> removeWishlistItem(@RequestParam Long productId) {
        wishlistItemsService.removeWishlistItem(productId);
        return new ResponseEntity<>("Wishlist item removed successfully", HttpStatus.OK);
    }

    @GetMapping("/get_wishlist_items")
    public ResponseEntity<List<WishlistItems>> findAllWishlistItems() {
         return new ResponseEntity<>(wishlistItemsService.findAllWishlistItems(), HttpStatus.OK);
    }

}
