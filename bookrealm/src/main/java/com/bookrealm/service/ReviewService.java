package com.bookrealm.service;

import com.bookrealm.exception.AppException;
import com.bookrealm.exception.ErrorCode;
import com.bookrealm.model.Book;
import com.bookrealm.model.Member;
import com.bookrealm.model.Review;
import com.bookrealm.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;


    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void addReview(Member member, Book book, String contents, int popular) {
        Review review = new Review();
        review.setMember(member);
        review.setBook(book);
        review.setContents(contents);
        review.setPopular(popular);
        review.setReportDate(LocalDateTime.now());

        reviewRepository.save(review);

    }

    public List<Review> findByMemberId(Long memberID){
        return reviewRepository.findByMemberId(memberID);
    }

    public List<Review> findByBookId(Long bookID){

        List<Review> searchResults = reviewRepository.findByBookId(bookID);

        if (searchResults.isEmpty()) {
            throw new AppException(ErrorCode.BOOK_NOT_FOUND, "검색 결과가 없습니다");
        }
        return searchResults;
    }

    public void updateReview(Long reviewId, String contents, int popular){
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);

        if(optionalReview.isPresent()){
            Review review = optionalReview.get();
            review.setContents(contents);
            review.setPopular(popular);
            review.setReportDate(LocalDateTime.now());

            reviewRepository.save(review);
        } else {
            throw new AppException(ErrorCode.REVIEW_NOT_FOUND,"리뷰ID가 없습니다.");
        }

    }
    public void deleteReview(Long reviewId){
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);

        if(optionalReview.isPresent()){
            Review review = optionalReview.get();
            reviewRepository.delete(review);
        } else {
            throw new AppException(ErrorCode.REVIEW_NOT_FOUND,"리뷰ID가 없습니다.");

        }

    }

    public Review findById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }
}