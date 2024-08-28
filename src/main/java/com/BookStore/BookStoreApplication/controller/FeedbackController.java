package com.BookStore.BookStoreApplication.controller;

import com.BookStore.BookStoreApplication.model.FeedBack;
import com.BookStore.BookStoreApplication.service.FeedbackImplementation;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/bookstore_user")
public class FeedbackController {

    @Autowired
    FeedbackImplementation feedbackImplementation;

    @PostMapping("/add/feedback")
    public FeedBack addProductFeedback(@RequestParam long productId , @RequestBody FeedBack feedBack)
    {
        feedBack.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        return feedbackImplementation.addFeedback(productId,feedBack);
    }
    @GetMapping("/get/feedback")
    public List<FeedBack> getAllFeedback()
    {
        return feedbackImplementation.findAllFeedback();
    }


}
