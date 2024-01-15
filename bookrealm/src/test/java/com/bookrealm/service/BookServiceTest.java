package com.bookrealm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bookrealm.exception.AppException;
import com.bookrealm.model.Book;
import com.bookrealm.repository.BookRepository;

import jakarta.transaction.Transactional;
import org.springframework.util.Assert;

@SpringBootTest
@Transactional
class BookServiceTest {

    @Autowired
    BookService bookService;
    
    @Autowired
    BookRepository bookRepository;

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
    
    @Test
    public void 도서_추가() {
    	
    	// given
    	Book newBook = new Book();
    	newBook.setPrice(15000);
    	newBook.setStock(50);
    	newBook.setSales(150);
    	newBook.setCategory("소설");
    	newBook.setWriter("snow");
    	newBook.setTitle("눈이 오는 날");
    	newBook.setPublisher("하나출판");
    	newBook.setPublishDate(LocalDate.of(2024,1,9));
    	newBook.setImage("http://bookthumb.phinf.naver.net/cover/108/346/10834650.jpg?type=m1&udate=20160902");
    	newBook.setDescription("snow 작가가 출간한 소설 눈이 오는 날 입니다.");
    	
    	// When
        Book savedBook = bookRepository.save(newBook);
        
        // Then
        assertNotNull(savedBook.getId());
        assertEquals(newBook.getTitle(), savedBook.getTitle());
        assertEquals(newBook.getWriter(), savedBook.getWriter());
        assertEquals(newBook.getCategory(), savedBook.getCategory());
        assertEquals(newBook.getPrice(), savedBook.getPrice());
        assertEquals(newBook.getStock(), savedBook.getStock());
        assertEquals(newBook.getSales(), savedBook.getSales());
        assertEquals(newBook.getPublisher(), savedBook.getPublisher());
        assertEquals(newBook.getPublishDate(), savedBook.getPublishDate());
        assertEquals(newBook.getImage(), savedBook.getImage());
        assertEquals(newBook.getDescription(), savedBook.getDescription());

    }
    
    @Test
    public void 도서_제목작가_검색() {

    	// 성공적인 검색
        List<Book> searchResults = bookRepository.findByTitleAndWriter("snow");
        assertNotNull(searchResults);

    }
    
    @Test
    public void 도서_상세_조회() {

        Book book = savedBook();
    	
        // 성공적인 도서 상세 정보 조회
        Book bookDetail = bookService.findBookById(book.getId());
        assertNotNull(bookDetail);
    }
    
    @Test
    public void 베스트셀러_조회() {
    	
        // 성공적인 베스트셀러 도서 조회
        List<Book> bestSellers = bookService.findBestSellers();
        assertNotNull(bestSellers);
        assertFalse(bestSellers.isEmpty());
        
    }

}