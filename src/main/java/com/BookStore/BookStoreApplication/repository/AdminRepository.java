package com.BookStore.BookStoreApplication.repository;

import com.BookStore.BookStoreApplication.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {

     @Query("SELECT a from Admin a where a.adminName = :adminName")
    Admin findByName(@Param("adminName") String admin);
}
