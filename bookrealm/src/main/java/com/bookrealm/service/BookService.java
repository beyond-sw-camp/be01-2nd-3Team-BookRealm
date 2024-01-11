package com.bookrealm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bookrealm.exception.AppException;
import com.bookrealm.exception.ErrorCode;
import com.bookrealm.model.Book;
import com.bookrealm.repository.BookRepository;

@Service
public class BookService {

	private final BookRepository bookRepository;

	@Autowired
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	// 모든 도서 조회 (페이징)
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
	
//	// 제목 또는 작가에 특정 키워드를 포함하는 도서 조회
//	public List<Book> findBooksByTitleOrWriter(String searchTerm) {
//		return bookRepository.findByTitleAndWriter(searchTerm);
//	}
	
	// 제목 또는 작가에 특정 키워드를 포함하는 도서 조회
    public List<Book> findBooksByTitleOrWriter(String searchTerm) {
        List<Book> searchResults = bookRepository.findByTitleAndWriter(searchTerm);

        // 검색 결과 없음
        if (searchResults.isEmpty()) {  
            // 검색 결과가 없을 때의 동작을 처리 (예: 로깅, 특정 메시지를 모델에 추가 등)
            // 예외를 던지지 않고, 검색 결과가 없을 때의 특별한 동작을 수행하도록 처리
            // 여기에서는 아무 동작도 수행하지 않고 그대로 빈 결과를 반환
        }

        return searchResults;
    }

	// 판매량 기준으로 상위 5개 도서 조회 (페이징)
	public List<Book> findBestSellers() {
		Pageable pageable = PageRequest.of(0, 5);
		Page<Book> bestSellersPage = bookRepository.findBestSellers(pageable);
		return bestSellersPage.getContent();
	}
	
	// book_id로 도서 조회 (도서 상세페이지로 가기 위함)
	public Optional<Book> findBookById(Long id) {
		return bookRepository.findById(id);
	}

}
