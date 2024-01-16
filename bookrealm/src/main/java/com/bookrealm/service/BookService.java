package com.bookrealm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bookrealm.model.Book;
import com.bookrealm.repository.BookRepository;

@Service
public class BookService {

	private final BookRepository bookRepository;

	@Autowired
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	// 모든 도서 조회
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
	
	// 제목 또는 작가에 특정 키워드를 포함하는 도서 조회
    public List<Book> findBooksByTitleOrWriter(String searchTerm) {
        List<Book> searchResults = bookRepository.findByTitleAndWriter(searchTerm);

        // 검색 결과 없음
        if (searchResults.isEmpty()) {  
        }

        return searchResults;
    }

	// 판매량 기준으로 상위 5개 도서 조회
	public List<Book> findBestSellers() {
		Pageable pageable = PageRequest.of(0, 5);
		Page<Book> bestSellersPage = bookRepository.findBestSellers(pageable);
		return bestSellersPage.getContent();
	}
	
	// book_id로 도서 조회 (도서 상세페이지로 가기 위함)
	public Book findBookById(Long id) {
		return bookRepository.findById(id).orElse(null);
	}

}
