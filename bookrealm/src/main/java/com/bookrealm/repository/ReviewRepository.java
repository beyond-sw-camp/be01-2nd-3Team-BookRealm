package com.bookrealm.repository;

import com.bookrealm.model.Review;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMemberId(Long memberID);
    List<Review> findByBookId(Long bookID);

    List<Review> findByBookIdOrderByReportDateDesc(Long bookId);
    List<Review> findByMemberIdOrderByReportDateDesc(Long bookId);



}
