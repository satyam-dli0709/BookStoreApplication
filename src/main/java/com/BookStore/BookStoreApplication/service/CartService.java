package com.BookStore.BookStoreApplication.service;

import com.BookStore.BookStoreApplication.model.Cart;
import com.BookStore.BookStoreApplication.model.CartItem;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartService {

    Cart addProductToCart(Long productId, Cart cart);
    ResponseEntity<String> updateProduct(long cartItemId, int quantity);
    void removeCartItem(long cartItemId);
    List<CartItem> findAllCartItem();
}
