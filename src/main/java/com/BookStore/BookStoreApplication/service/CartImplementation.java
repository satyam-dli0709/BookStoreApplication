package com.BookStore.BookStoreApplication.service;

import com.BookStore.BookStoreApplication.exception.CustomInvalidException;
import com.BookStore.BookStoreApplication.model.CartItem;
import com.BookStore.BookStoreApplication.model.Product;
import com.BookStore.BookStoreApplication.repository.CartItemRepository;
import com.BookStore.BookStoreApplication.repository.CartRepository;
import com.BookStore.BookStoreApplication.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartImplementation implements CartService{

    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public CartItem addProductToCart(Long productId)
    {
        Product product = productRepository.findById(productId).orElseThrow(()->new CustomInvalidException("No product is present "));

        if(product.getStock()==0)
        {
            throw new CustomInvalidException("Product is out of Stock");
        }

        CartItem cartItem = new CartItem();
        cartItem.setProducts(product);
        cartItem.setQuantity(1);
        cartItemRepository.save(cartItem);
        product.setCartItem(cartItem);
        return cartItem;
    }

    @Override
    public ResponseEntity<String> updateProduct(long cartItemId, int quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(()->new CustomInvalidException("No cart is present"));

        int stock = cartItem.getProducts().getStock();
        if(stock < quantity)
        {
            throw new CustomInvalidException("InSufficient stock");
        }
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
        return ResponseEntity.ok("Quantity updated");

    }

    @Override
    public void removeCartItem(long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(()-> new CustomInvalidException("CartItem not found"));
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public List<CartItem> findAllCartItem() {
        return cartItemRepository.findAll();
    }


}
