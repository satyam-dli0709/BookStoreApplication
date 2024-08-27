package com.BookStore.BookStoreApplication.controller;

import com.BookStore.BookStoreApplication.exception.ProductNotFoundException;
import com.BookStore.BookStoreApplication.model.Admin;
import com.BookStore.BookStoreApplication.model.Product;
import com.BookStore.BookStoreApplication.service.AdminService;
import com.BookStore.BookStoreApplication.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookstore_user/admin")
public class AdminController {
@Autowired
    private AdminService adminService;

@Autowired
private ProductService productService;


@PostMapping("/registration")
    public Admin registerAdmin (@RequestBody Admin admin){
       return adminService.registerAdmin(admin);
}

@PostMapping("/login")
    public Admin loginAdmin (@RequestParam String adminName ,@RequestParam String password ){
   return  adminService.loginAdmin(adminName,password);
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
