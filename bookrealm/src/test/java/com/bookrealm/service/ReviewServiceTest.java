package com.bookrealm.service;

import com.bookrealm.model.Book;
import com.bookrealm.model.Member;
import com.bookrealm.model.Review;
import com.bookrealm.model.dto.ReviewDto;
import com.bookrealm.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewServiceTest {
    @Autowired
    ReviewService reviewService;

    @Autowired
    ReviewRepository reviewRepository;

    private Member member;
    private Book book;

    @BeforeEach
    public void setUp(){
        member = new Member();
        book = new Book();
    }

    @Test
    public void 리뷰쓰기() {
        //given
        member.setId(1L);
        book.setId(1L);

        String contents = "너무 재미있따.";
        int popular = 5;

        ReviewDto reviewDto = new ReviewDto(contents,popular);


        //when
        reviewService.addReview(member,book,reviewDto.getContents(),reviewDto.getPopular());

        //then
        Review savedReview = reviewRepository.findAll().get(0);

        assertEquals(member.getId(), savedReview.getMember().getId());
        assertEquals(book.getId(), savedReview.getBook().getId());
        assertEquals(reviewDto.getContents(), savedReview.getContents());
        assertEquals(reviewDto.getPopular(), savedReview.getPopular());
        assertNotNull(savedReview.getReportDate());


    }
    @Test
    public void 유저별리뷰검색(){
        //given
        member.setId(1L);
        book.setId(1L);

        String contents = "너무 재미있따.";
        int popular = 5;

        ReviewDto reviewDto = new ReviewDto(contents,popular);
        reviewService.addReview(member,book,reviewDto.getContents(),reviewDto.getPopular());

        //when
        List<Review> reviews = reviewService.findByMemberId(member.getId());

        for(Review review : reviews){
            assertNotNull(review.getContents());
            assertNotNull(review.getPopular());
            assertNotNull(review.getReportDate());
        }


    }

    @Test
    public void 도서별리뷰검색(){
        //given
        member.setId(1L);
        book.setId(1L);

        String contents = "너무 재미있따.";
        int popular = 5;

        ReviewDto reviewDto = new ReviewDto(contents,popular);
        reviewService.addReview(member,book,reviewDto.getContents(),reviewDto.getPopular());

        //when
        List<Review> reviews = reviewService.findByBookId(book.getId());

        for(Review review : reviews){
            assertNotNull(review.getContents());
            assertNotNull(review.getPopular());
            assertNotNull(review.getReportDate());
        }
    }

    @Test
    public void 리뷰수정(){
        // given
        Review originalReview = new Review();
        member.setId(1L);
        book.setId(1L);
        originalReview.setId(4L);
        // when  수정할 내용
        String newContents = "Updated Contents!!!!!!";
        int newPopular = 3;
        ReviewDto reviewDto = new ReviewDto(newContents, newPopular);
        reviewService.updateReview(originalReview.getId(), reviewDto.getContents(), reviewDto.getPopular());

        // then
        Review updatedReview = reviewService.findById(originalReview.getId());
        assertNotNull(updatedReview);
        assertEquals(newContents, updatedReview.getContents());
        assertEquals(newPopular, updatedReview.getPopular());

    }

    @Test
    public void 리뷰삭제(){
        // given
        Review originalReview = new Review();
        member.setId(1L);
        book.setId(1L);
        originalReview.setId(4L);

        //when
        reviewService.deleteReview(originalReview.getId());

        //then
        Review deletedReview = reviewService.findById(originalReview.getId());
        assertNull(deletedReview);

    }
}