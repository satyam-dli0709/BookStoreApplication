package com.BookStore.BookStoreApplication.service;

import com.BookStore.BookStoreApplication.model.Admin;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    public Admin registerAdmin (Admin admin);
    public Admin loginAdmin (String adminName , String password);
}