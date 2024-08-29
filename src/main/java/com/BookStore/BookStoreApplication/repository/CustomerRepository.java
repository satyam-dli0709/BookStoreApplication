package com.BookStore.BookStoreApplication.repository;

import com.BookStore.BookStoreApplication.model.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDetails, Long> {
    @Query("select c from CustomerDetails c where c.user.userId = :userId")
    CustomerDetails findByUserUserId(Long userId);

}


