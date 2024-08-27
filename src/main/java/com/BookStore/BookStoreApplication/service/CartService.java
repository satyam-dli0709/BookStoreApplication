package com.BookStore.BookStoreApplication.service;

import com.BookStore.BookStoreApplication.model.CartItem;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartService {

    CartItem addProductToCart(Long productId);
    ResponseEntity<String> updateProduct(long cartItemId, int quantity);
    void removeCartItem(long cartItemId);
    List<CartItem> findAllCartItem();
}
