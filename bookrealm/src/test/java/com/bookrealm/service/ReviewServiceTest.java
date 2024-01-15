package com.bookrealm.service;

import com.bookrealm.model.Address;
import com.bookrealm.model.Book;
import com.bookrealm.model.Member;
import com.bookrealm.model.Review;
import com.bookrealm.model.dto.ReviewDto;
import com.bookrealm.repository.BookRepository;
import com.bookrealm.repository.MemberRepository;
import com.bookrealm.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.bookrealm.model.SuType.NORMAL;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReviewServiceTest {
    @Autowired
    ReviewService reviewService;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    MemberRepository memberRepository;

    @Transactional
    public Book savedBook() {
        Book newBook = new Book();
        newBook.setPrice(12000);
        newBook.setStock(2);
        newBook.setSales(500);
        newBook.setCategory("소설");
        newBook.setWriter("J. K. 롤링");
        newBook.setTitle("해리 포터와 마법사의 돌 1");
        newBook.setPublisher("문학수첩");
        newBook.setPublishDate(LocalDate.of(2019,11,19));
        newBook.setImage("http://bookthumb.phinf.naver.net/cover/108/346/10834650.jpg?type=m1&udate=20160902");
        newBook.setDescription("1999년 <해리 포터와 마법사의 돌>의 출간을 필두로 지금까지(2019년 9월 기준) 약 1,500만 부가 판매되었으며, 현재에도 독자들에게 변함없는 사랑을 받고 있다.");
        return bookRepository.save(newBook);
    }

    @Transactional
    public Member savedMember() {
        Member newMember = new Member();
        newMember.setEmail("bb@naver.com");
        newMember.setUsername("김단아");
        newMember.setPasswd("1111");
        newMember.setAddress(new Address("123","시흥시", "대은로", "62"));
        newMember.setPhone("01023456789");
        newMember.setSuType(NORMAL);
        return memberRepository.save(newMember);
    }

    @Transactional
    public Review saveReview(){
        Review review = new Review();
        review.setId(1L);
        review.setBook(savedBook());
        review.setMember(savedMember());
        review.setContents("굿굿굿굿");
        review.setPopular(5);
        review.setReportDate(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    @Test
    public void 리뷰쓰기() {
        //given

        Member member = savedMember();
        Book book = savedBook();
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
        Member member = savedMember();
        Book book = savedBook();

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
        Member member = savedMember();
        Book book = savedBook();

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
        Review review = saveReview();
        Book book = savedBook();

        // when  수정할 내용
        String newContents = "노노노 굿굿굿굿";
        int newPopular = 4;
        ReviewDto reviewDto = new ReviewDto(newContents, newPopular);
        reviewService.updateReview(review.getId(), reviewDto.getContents(), reviewDto.getPopular());

        // then
        Review updatedReview = reviewService.findById(review.getId());
        assertNotNull(updatedReview);
        assertEquals(newContents, updatedReview.getContents());
        assertEquals(newPopular, updatedReview.getPopular());

    }

    @Test
    public void 리뷰삭제(){
        // given
        Review originalReview = saveReview();

        //when
        reviewService.deleteReview(originalReview.getId());

        //then
        Review deletedReview = reviewService.findById(originalReview.getId());
        assertNull(deletedReview);

    }
}