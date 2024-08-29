package com.BookStore.BookStoreApplication.service;

import com.BookStore.BookStoreApplication.model.FeedBack;

import java.util.List;

public interface FeedbackService {

    FeedBack addFeedback(long productId , FeedBack feedBack);
    List<FeedBack> getByProductId(long productId);
}
