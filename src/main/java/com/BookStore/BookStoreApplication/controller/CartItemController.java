package com.BookStore.BookStoreApplication.controller;

import com.BookStore.BookStoreApplication.model.Cart;
import com.BookStore.BookStoreApplication.model.CartItem;
import com.BookStore.BookStoreApplication.model.User;
import com.BookStore.BookStoreApplication.service.CartImplementation;
import jakarta.validation.Valid;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/bookstore_user")
public class CartItemController {

    @Autowired
    CartImplementation cartImplementation;

    @PostMapping("/add_cart_item")
    public Cart addProduct(@RequestParam long productId,@Valid @RequestBody Cart cart)
    {
        cart.setCartTimeStamp(new Timestamp(System.currentTimeMillis()));
//        cart.getUser().setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return cartImplementation.addProductToCart(productId,cart);
    }

    @PatchMapping("/cart_item_quantity")
    public ResponseEntity<String> updateQuantity(@RequestParam long cartItemId , @RequestParam int quantity)
    {
        return cartImplementation.updateProduct(cartItemId,quantity);
    }

    @DeleteMapping("/remove_cart_item")
    public void removeCartItem(@RequestParam long cartItemId)
    {
        cartImplementation.removeCartItem(cartItemId);
    }

    @GetMapping("/get_cart_items")
    public List<CartItem> getAllCartItem()
    {
        return cartImplementation.findAllCartItem();
    }



}
