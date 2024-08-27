package com.BookStore.BookStoreApplication.service;

import com.BookStore.BookStoreApplication.exception.CustomInvalidException;
import com.BookStore.BookStoreApplication.model.Admin;
import com.BookStore.BookStoreApplication.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AdminServiceImpl implements  AdminService{
    @Autowired
    private AdminRepository adminRepository;
    @Override
    public Admin registerAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Admin loginAdmin(String adminName , String password) {
      //  Optional<Admin> optionalAdmin = Optional.ofNullable(adminRepository.findByName(adminName));

        long adminId = adminRepository.findByName(adminName);
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

}
