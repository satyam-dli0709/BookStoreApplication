package com.BookStore.BookStoreApplication.repository;

import com.BookStore.BookStoreApplication.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
