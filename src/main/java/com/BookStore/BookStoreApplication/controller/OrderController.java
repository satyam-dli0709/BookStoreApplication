package com.BookStore.BookStoreApplication.controller;

import com.BookStore.BookStoreApplication.model.Order;
import com.BookStore.BookStoreApplication.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookstore_user")
public class OrderController {
//   @Autowired
//    OrderService orderService ;
//   @Autowired
//   OrderProcessingService orderProcessingService;
//
//
//   @PostMapping("/add/order")
//   public Order addOrder(@RequestParam long userId, @RequestBody List<OrderItems> orders)
//   {
//       return orderProcessingService.placeOrder(userId,orders);
//   }
@Autowired
OrderService orderService;
    @PostMapping("/add/order")
    public Order placeOrder(@RequestBody Long userId) {
        return orderService.placeOrder(userId);
    }




}
