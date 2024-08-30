package com.BookStore.BookStoreApplication.service;

import com.BookStore.BookStoreApplication.model.Product;
import com.BookStore.BookStoreApplication.repository.ProductRepository;
import org.springdoc.core.configuration.oauth2.SpringDocOidcClientRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getLowToHigh() {
        return productRepository.findLowToHigh();
    }

    @Override
    public List<Product> getHighToLow() {
        return productRepository.findHighToLow();
    }

    @Override
    public List<Product> getNewestArival() {
        return productRepository.findNewestArival();
    }


}
