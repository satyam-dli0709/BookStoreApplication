package com.BookStore.BookStoreApplication.repository;

import com.BookStore.BookStoreApplication.model.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.nio.file.LinkOption;
import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedBack, Long> {

    @Query("select f from FeedBack f where f.user.userId =:userId")
    FeedBack findByUserId(long userId);

    @Query("select f from FeedBack f where f.product.id = :productId")
    List<FeedBack> findByProductId(long productId);
}
