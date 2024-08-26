package com.BookStore.BookStoreApplication.exception;

public class CustomInvalidException extends RuntimeException{
    public CustomInvalidException(String message)
    {
        super(message);
    }
}
