package com.BookStore.BookStoreApplication.service;

import com.BookStore.BookStoreApplication.model.FeedBack;

public interface FeedbackService {

    FeedBack addFeedback(long productId , FeedBack feedBack);
}
