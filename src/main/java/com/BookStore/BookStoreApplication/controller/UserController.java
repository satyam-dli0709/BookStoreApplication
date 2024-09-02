package com.BookStore.BookStoreApplication.controller;

import com.BookStore.BookStoreApplication.DTO.LoginDTO;
import com.BookStore.BookStoreApplication.model.User;
import com.BookStore.BookStoreApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@CrossOrigin
@RequestMapping("/bookstore_user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO) {
        if(userService.loginUser(loginDTO.getUsername(), loginDTO.getPassword()))
        {
            return ResponseEntity.ok("User logged in successfully");
        }
        else
        {
            return ResponseEntity.ok("Invalid credintial");
        }

    }


}