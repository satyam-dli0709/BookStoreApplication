package com.BookStore.BookStoreApplication.controller;

import com.BookStore.BookStoreApplication.model.Admin;
import com.BookStore.BookStoreApplication.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookstore_user/admin")
public class AdminController {
@Autowired
    private AdminService adminService;

@PostMapping("/registration")
    public Admin registerAdmin (@RequestBody Admin admin){
       return adminService.registerAdmin(admin);
}

@PostMapping("/login")
    public Admin loginAdmin (@RequestParam String adminName ,@RequestParam String password ){
   return  adminService.loginAdmin(adminName,password);

}

}
