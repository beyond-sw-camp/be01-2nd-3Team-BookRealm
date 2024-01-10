package com.bookrealm.service;

import com.bookrealm.model.Book;
import com.bookrealm.model.Member;
import com.bookrealm.model.Review;
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

//    public void addReview(User user, Book book, String contents, int popular){
//        Review review = new Review();
//        review.setUser(user);
//        review.setBook(book);
//        review.setContents(contents);
//        review.setPopular(popular);
//        review.setReportDate(LocalDateTime.now());
//
//        reviewRepository.save(review);

    public void addReview(String contents, int popular){
        Review review = new Review();

        // 고정된 유저와 책 데이터를 사용
        Member member = getFixedUser();
        Book book = getFixedBook();

        review.setMember(member);
        review.setBook(book);
        review.setContents(contents);
        review.setPopular(popular);
        review.setReportDate(LocalDateTime.now());

        reviewRepository.save(review);
    }

    private Member getFixedUser() {
        // 여기에서 고정된 유저 데이터를 생성 또는 가져옴
        // 예시로 간단히 고정된 값을 사용하거나, 실제 데이터베이스에서 가져올 수 있음
        Member member = new Member();
        member.setId(1L);
        // 다른 필드들도 설정

        return member;
    }

    private Book getFixedBook() {
        // 여기에서 고정된 책 데이터를 생성 또는 가져옴
        // 예시로 간단히 고정된 값을 사용하거나, 실제 데이터베이스에서 가져올 수 있음
        Book book = new Book();
        book.setId(1L);
        // 다른 필드들도 설정

        return book;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

}
