package com.BookStore.BookStoreApplication.repository;

import com.BookStore.BookStoreApplication.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {

     @Query("SELECT a.adminId from Admin a where a.adminName = :admin")
     Optional<Long> findByName(String admin);
}
