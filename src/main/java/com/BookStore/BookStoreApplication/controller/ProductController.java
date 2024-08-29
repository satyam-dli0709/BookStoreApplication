package com.BookStore.BookStoreApplication.controller;

import com.BookStore.BookStoreApplication.exception.ProductNotFoundException;
import com.BookStore.BookStoreApplication.model.Product;
import com.BookStore.BookStoreApplication.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookstore_user")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/get/books")
    public ResponseEntity<Object> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }
}
