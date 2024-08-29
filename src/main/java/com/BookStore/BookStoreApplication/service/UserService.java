package com.BookStore.BookStoreApplication.service;

import com.BookStore.BookStoreApplication.exception.CustomInvalidException;
import com.BookStore.BookStoreApplication.model.User;
import com.BookStore.BookStoreApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User registerUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String username = user.getUsername();
        if (userRepository.findByUsername(username)!=null) {
            throw new CustomInvalidException("User with this Username already exists.");
        }
        User saveUser=userRepository.save(user);
        return saveUser;
    }
    public boolean loginUser(String userName , String password)
    {
        User user1 = userRepository.findByUsername(userName);
        User user = userRepository.findById(user1.getUserId()).orElse(null);


        if (user != null) {
            return passwordEncoder.matches(password, user.getUsername());
        }
        else
        {
           throw  new CustomInvalidException("Invalid UserName and Password");
        }
    }
}