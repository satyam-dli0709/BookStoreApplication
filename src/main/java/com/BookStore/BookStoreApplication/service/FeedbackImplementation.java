package com.BookStore.BookStoreApplication.service;

import com.BookStore.BookStoreApplication.exception.CustomInvalidException;
import com.BookStore.BookStoreApplication.model.Cart;
import com.BookStore.BookStoreApplication.model.FeedBack;
import com.BookStore.BookStoreApplication.model.Product;
import com.BookStore.BookStoreApplication.model.User;
import com.BookStore.BookStoreApplication.repository.FeedbackRepository;
import com.BookStore.BookStoreApplication.repository.ProductRepository;
import com.BookStore.BookStoreApplication.repository.UserRepository;
import jakarta.persistence.PersistenceContext;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackImplementation implements FeedbackService{

    @Autowired
    FeedbackRepository feedbackRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public FeedBack addFeedback(long productId,FeedBack feedBack) {
        User user = userRepository.findById(feedBack.getUser().getUserId()).orElseThrow(()-> new CustomInvalidException("User not found with the given id"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new CustomInvalidException("Product not found"));


        FeedBack feedBack1 = new FeedBack();
        feedBack1.setProduct(product);
        feedBack1.setUser(user);
        feedBack1.setText(feedBack.getText());
        feedBack1.setCreatedAt(feedBack.getCreatedAt());
        feedBack1.setRating(feedBack.getRating());

        return feedbackRepository.save(feedBack1);

    }

    public List<FeedBack> findAllFeedback()
    {
        return feedbackRepository.findAll();
    }

}
