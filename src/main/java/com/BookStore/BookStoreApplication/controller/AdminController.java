package com.BookStore.BookStoreApplication.controller;

import com.BookStore.BookStoreApplication.exception.ProductNotFoundException;
import com.BookStore.BookStoreApplication.model.Admin;
import com.BookStore.BookStoreApplication.model.Order;
import com.BookStore.BookStoreApplication.model.Product;
import com.BookStore.BookStoreApplication.service.AdminService;
import com.BookStore.BookStoreApplication.service.AdminServiceImpl;
import com.BookStore.BookStoreApplication.service.AdminServiceImpl;
import com.BookStore.BookStoreApplication.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/bookstore_user/admin")
public class AdminController {
@Autowired
    private AdminService adminService;

@Autowired
private ProductService productService;



    @PostMapping("/registration")
    public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin) {
        admin.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        Admin registeredAdmin = adminService.registerAdmin(admin);
        return ResponseEntity.ok(registeredAdmin);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginAdmin(@RequestParam String adminName, @RequestParam String password) {
        adminService.loginAdmin(adminName, password);
        return ResponseEntity.ok("admin logged in successfully");
    }


    @PostMapping("/add/book")
    public ResponseEntity<Object> addProduct(@Valid @RequestBody Product product) {
        productService.addProduct(product);
        return new ResponseEntity<>("Added Successfully", HttpStatus.CREATED);
    }

    @PutMapping("/update/book/{product_id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("product_id") long id, @RequestBody Product product) {
        if (productService.getAllProducts().stream().noneMatch(p -> p.getId() == id)) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        } else {
            product.setId(id);
            productService.updateProduct(product);
            return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/book")
    public ResponseEntity<Object> deleteProduct(@RequestParam long id) {
        if (productService.getAllProducts().stream().noneMatch(product -> product.getId() == id)) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
//import java.util.List;



    else {
            productService.deleteProduct(id);
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        }
    }

    @GetMapping("/get/orders")
    public List<Order> getAllOrders()
    {
        return adminService.getAllOrder();
    }




}
