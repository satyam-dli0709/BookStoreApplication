package com.BookStore.BookStoreApplication.service;

import com.BookStore.BookStoreApplication.exception.CustomInvalidException;
import com.BookStore.BookStoreApplication.model.Admin;
import com.BookStore.BookStoreApplication.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements  AdminService{
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public Admin registerAdmin(Admin admin) {

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        String username = admin.getAdminName();
        if (adminRepository.findByName(username).isPresent()) {
            throw new CustomInvalidException("Admin with this Username already exists.");
        }
        Admin saveAdmin=adminRepository.save(admin);

        return saveAdmin;
    }

    @Override
    public boolean loginAdmin(String adminName , String password) {
        long adminId =   adminRepository.findByName(adminName)
                .orElseThrow(() -> new CustomInvalidException("Admin not found with name: " + adminName));
        Admin admin = adminRepository.findById(adminId).orElseThrow(()->new CustomInvalidException("Admin not found"));


        if (admin != null) {
            return passwordEncoder.matches(password, admin.getAdminName());
        }
        else
        {
            throw  new CustomInvalidException("Invalid UserName and Password");
        }

        }

}
