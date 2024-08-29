package com.BookStore.BookStoreApplication.service;

import com.BookStore.BookStoreApplication.exception.CustomInvalidException;
import com.BookStore.BookStoreApplication.model.Admin;
import com.BookStore.BookStoreApplication.model.Order;
import com.BookStore.BookStoreApplication.repository.AdminRepository;
import com.BookStore.BookStoreApplication.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements  AdminService{
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    PasswordEncoder passwordEncoder;



    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Admin registerAdmin(Admin admin) {

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        String username = admin.getAdminName();
        if (adminRepository.findByName(username)!=null) {
            throw new CustomInvalidException("Admin with this Username already exists.");
        }
        return adminRepository.save(admin);

    }

    @Override
    public boolean loginAdmin(String adminName , String password) {
        Admin admin1 =   adminRepository.findByName(adminName);
        if(admin1==null)
        {
            throw new CustomInvalidException("Admin not found");
        }
        Admin admin = adminRepository.findById(admin1.getAdminId()).orElseThrow(()->new CustomInvalidException("Admin not found"));


        if (admin != null) {
            return passwordEncoder.matches(password, admin.getPassword());
        }
        else
        {
            throw  new CustomInvalidException("Invalid UserName and Password");
        }

        }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

}
