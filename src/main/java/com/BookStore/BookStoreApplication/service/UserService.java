package com.BookStore.BookStoreApplication.service;

import com.BookStore.BookStoreApplication.exception.CustomInvalidException;
import com.BookStore.BookStoreApplication.model.User;
import com.BookStore.BookStoreApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }
    public User loginUser(String userName , String password)
    {
        User userId = userRepository.findByUsername(userName);
        User user = userRepository.findById(userId.getUserId()).orElseThrow(()->new CustomInvalidException("User not found"));

        if(user.getPassword().equals(password))
        {
            return user;
        }
        else
        {
            throw new CustomInvalidException("Invalid Password");
        }

    }
}