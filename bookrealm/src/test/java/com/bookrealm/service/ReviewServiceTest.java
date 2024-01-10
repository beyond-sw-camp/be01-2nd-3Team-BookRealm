package com.bookrealm.service;

import com.bookrealm.model.Book;
import com.bookrealm.model.Review;
import com.bookrealm.model.User;
import com.bookrealm.model.dto.ReviewDto;
import com.bookrealm.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewServiceTest {
    @Autowired
    ReviewService reviewService;

    @Autowired
    ReviewRepository reviewRepository;

    private User user;
    private Book book;

    @BeforeEach
    public void setUp(){
        user = new User();
        book = new Book();
    }

    @Test
    public void 리뷰쓰기() {
        //given
        user.setId(1L);
        book.setId(1L);

        String contents = "너무 재미있따.";
        int popular = 5;

        ReviewDto reviewDto = new ReviewDto(contents,popular);


        //when
        reviewService.addReview(user,book,reviewDto.getContents(),reviewDto.getPopular());

        //then
        Review savedReview = reviewRepository.findAll().get(0);

        assertEquals(user.getId(), savedReview.getUser().getId());
        assertEquals(book.getId(), savedReview.getBook().getId());
        assertEquals(reviewDto.getContents(), savedReview.getContents());
        assertEquals(reviewDto.getPopular(), savedReview.getPopular());
        assertNotNull(savedReview.getReportDate());


    }
    @Test
    public void 유저별리뷰검색(){
        //given
        user.setId(1L);
        book.setId(1L);

        String contents = "너무 재미있따.";
        int popular = 5;

        ReviewDto reviewDto = new ReviewDto(contents,popular);
        reviewService.addReview(user,book,reviewDto.getContents(),reviewDto.getPopular());

        //when
        List<Review> reviews = reviewService.findByUserId(user.getId());

        for(Review review : reviews){
            assertNotNull(review.getContents());
            assertNotNull(review.getPopular());
            assertNotNull(review.getReportDate());
        }


    }

    @Test
    public void 도서별리뷰검색(){
        //given
        user.setId(1L);
        book.setId(1L);

        String contents = "너무 재미있따.";
        int popular = 5;

        ReviewDto reviewDto = new ReviewDto(contents,popular);
        reviewService.addReview(user,book,reviewDto.getContents(),reviewDto.getPopular());

        //when
        List<Review> reviews = reviewService.findByBookId(book.getId());

        for(Review review : reviews){
            assertNotNull(review.getContents());
            assertNotNull(review.getPopular());
            assertNotNull(review.getReportDate());
        }


    }
}