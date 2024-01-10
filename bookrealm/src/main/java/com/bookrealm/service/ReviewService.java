package com.bookrealm.service;

import com.bookrealm.model.Book;
import com.bookrealm.model.Review;
import com.bookrealm.model.User;
import com.bookrealm.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void addReview(User user, Book book, String contents, int popular) {
        Review review = new Review();
        review.setUser(user);
        review.setBook(book);
        review.setContents(contents);
        review.setPopular(popular);
        review.setReportDate(LocalDateTime.now());

        reviewRepository.save(review);

    }

    public List<Review> findByUserId(Long userID){
        return reviewRepository.findByUserId(userID);
    }

    public List<Review> findByBookId(Long bookID){
        return reviewRepository.findByBookId(bookID);
    }
    
}
