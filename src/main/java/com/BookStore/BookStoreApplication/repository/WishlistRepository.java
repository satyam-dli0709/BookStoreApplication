package com.BookStore.BookStoreApplication.repository;

import com.BookStore.BookStoreApplication.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    @Query("SELECT w from Wishlist w where w.user.userId = :userId")
    Optional<Wishlist> findByUserId(@Param("userId") Long userId);


}
