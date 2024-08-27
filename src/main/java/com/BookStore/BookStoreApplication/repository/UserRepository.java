package com.BookStore.BookStoreApplication.repository;

import com.BookStore.BookStoreApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u.userId from User u where u.username = :username")
    long findByUsername(String username);
}