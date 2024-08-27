package com.BookStore.BookStoreApplication.repository;

import com.BookStore.BookStoreApplication.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.nio.file.LinkOption;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
