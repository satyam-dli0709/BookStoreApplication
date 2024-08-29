package com.BookStore.BookStoreApplication.service;

import com.BookStore.BookStoreApplication.exception.CustomInvalidException;
import com.BookStore.BookStoreApplication.model.Admin;
import com.BookStore.BookStoreApplication.model.Order;
import com.BookStore.BookStoreApplication.repository.AdminRepository;
import com.BookStore.BookStoreApplication.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AdminServiceImpl implements  AdminService{
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Admin registerAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Admin loginAdmin(String adminName , String password) {
      //  Optional<Admin> optionalAdmin = Optional.ofNullable(adminRepository.findByName(adminName));

//        long adminId = adminRepository.findByName(adminName);
//        if (adminRepository.findByName(adminName) == null){
//            throw new CustomInvalidException("admin not found");
//        }
        long adminId =   adminRepository.findByName(adminName)
                .orElseThrow(() -> new CustomInvalidException("Admin not found with name: " + adminName));
        Admin admin = adminRepository.findById(adminId).orElseThrow(()->new CustomInvalidException("Admin not found"));
        if(password.equals(admin.getPassword()))
            {
                return  admin;
            }
            else
            {
                throw new CustomInvalidException("Invalid credentials");
            }
        }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

}
