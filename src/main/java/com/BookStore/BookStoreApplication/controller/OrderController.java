package com.BookStore.BookStoreApplication.controller;

import com.BookStore.BookStoreApplication.model.Order;
import com.BookStore.BookStoreApplication.model.OrderItems;
import com.BookStore.BookStoreApplication.model.User;
import com.BookStore.BookStoreApplication.service.OrderProcessingService;
import com.BookStore.BookStoreApplication.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookstore_user")
public class OrderController {
@Autowired
OrderService orderService;
    @PostMapping("/add/order")
    public Order placeOrder(@RequestParam Long userId) {
        return orderService.placeOrder(userId);
    }

}
