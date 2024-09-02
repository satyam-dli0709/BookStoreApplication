package com.BookStore.BookStoreApplication.controller;

import com.BookStore.BookStoreApplication.model.Order;
import com.BookStore.BookStoreApplication.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
