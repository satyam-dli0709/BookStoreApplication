package com.BookStore.BookStoreApplication.repository;

import com.BookStore.BookStoreApplication.model.Product;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p order by p.price asc")
    List<Product> findLowToHigh();

    @Query("select p from Product p order by p.price desc")
    List<Product> findHighToLow();

    @Query("SELECT p FROM Product p ORDER BY p.created_at DESC")
    List<Product> findNewestArival();

}
