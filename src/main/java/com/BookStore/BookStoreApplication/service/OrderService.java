package com.BookStore.BookStoreApplication.service;

import com.BookStore.BookStoreApplication.model.Order;
import com.BookStore.BookStoreApplication.model.OrderItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface OrderService {

    Order placeOrder(long userId, List<OrderItems> cartItems);
}
