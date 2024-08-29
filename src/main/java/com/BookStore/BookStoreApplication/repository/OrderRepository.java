package com.BookStore.BookStoreApplication.repository;

import com.BookStore.BookStoreApplication.model.Cart;
import com.BookStore.BookStoreApplication.model.Order;
import com.BookStore.BookStoreApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("select o from Order o where o.user.userId =:userId")
    Order findByUser(long userId);
}
