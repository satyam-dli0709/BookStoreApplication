package com.BookStore.BookStoreApplication.repository;

import com.BookStore.BookStoreApplication.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    @Query("select c from Cart c where c.user.userId = :id ")
    Cart findByUserId(long id);
}
