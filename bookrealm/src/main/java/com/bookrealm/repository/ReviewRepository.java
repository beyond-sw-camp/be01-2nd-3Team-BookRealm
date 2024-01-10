package com.bookrealm.repository;

import com.bookrealm.model.Review;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUserId(Long userID);
    List<Review> findByBookId(Long bookID);



//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query(value = "SELECT contents,popular,reportDate FROM Review WHERE user.id = :userId")
//    List<Review> findByUserId(@Param("userid") Long userID);


}
