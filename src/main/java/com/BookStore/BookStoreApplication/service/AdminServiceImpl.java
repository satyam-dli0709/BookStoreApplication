package com.BookStore.BookStoreApplication.service;

import com.BookStore.BookStoreApplication.exception.CustomInvalidException;
import com.BookStore.BookStoreApplication.model.Admin;
import com.BookStore.BookStoreApplication.model.Order;
import com.BookStore.BookStoreApplication.repository.AdminRepository;
import com.BookStore.BookStoreApplication.repository.OrderRepository;
import com.BookStore.BookStoreApplication.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService, UserDetailsService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Admin registerAdmin(Admin admin) {
        String username = admin.getAdminName();
        if (adminRepository.findByName(username) != null) {
            throw new CustomInvalidException("Admin with this Username already exists.");
        }
        return adminRepository.save(admin);
    }

    @Override
    public boolean loginAdmin(String adminName, String password) {
        Admin admin1 = adminRepository.findByName(adminName);
        if (admin1 == null) {
            throw new CustomInvalidException("Admin not found");
        }
        Admin admin = adminRepository.findById(admin1.getAdminId()).orElseThrow(() -> new CustomInvalidException("Admin not found"));

        if (admin != null) {
            return password.equals(admin.getPassword());
        } else {
            throw new CustomInvalidException("Invalid UserName and Password");
        }
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByName(username);
        if (admin == null) {
            throw new UsernameNotFoundException("Admin not found");
        }
        return new org.springframework.security.core.userdetails.User(admin.getAdminName(), admin.getPassword(), new ArrayList<>());
    }

    public String generateToken(String username) {
        return jwtUtil.generateToken(username);
    }
}

