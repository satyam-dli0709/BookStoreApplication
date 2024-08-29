package com.BookStore.BookStoreApplication.service;

import com.BookStore.BookStoreApplication.model.Admin;
import com.BookStore.BookStoreApplication.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    public Admin registerAdmin (Admin admin);
    public boolean loginAdmin (String adminName , String password);
    public List<Order> getAllOrder();
}
