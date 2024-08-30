package com.BookStore.BookStoreApplication.service;

import com.BookStore.BookStoreApplication.model.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();
    public Product addProduct(Product product);
    public Product updateProduct(Product product);
    public void deleteProduct(long id);
    public List<Product> getLowToHigh();
    public List<Product> getHighToLow();
    public List<Product> getNewestArival();
}
