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
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Object> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping("/add/book")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) {
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);
    }

    @PutMapping("/update/book/{product_id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id,@RequestBody Product product) {
        if (productService.getAllProducts().stream().noneMatch(p -> p.getId() == id)) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        } else {
            product.setId(id);
            return new ResponseEntity<>(productService.updateProduct(product), HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/book/{product_id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable long id) {
        if (productService.getAllProducts().stream().noneMatch(product -> product.getId() == id)) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }else {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
