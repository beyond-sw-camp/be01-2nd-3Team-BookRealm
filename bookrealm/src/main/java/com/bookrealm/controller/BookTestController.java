package com.bookrealm.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookrealm.model.Book;
import com.bookrealm.service.BookService;

@RequestMapping("/test")
@Controller
public class BookTestController {

	private final BookService bookService;

	@Autowired
	public BookTestController(BookService bookService) {
		this.bookService = bookService;
	}

//    한 페이지에 리스트 출력
//    @GetMapping("/books")
//    public String findAll(Model model) {
//        List<Book> allBooks = bookService.findAll();
//        model.addAttribute("allBooks", allBooks);
//        return "index";  
//    }

	@GetMapping("/books")
	public String getAll(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
		@RequestParam(name = "size", defaultValue = "20") int size) {  // page : 현재 페이지 번호, size : 한 페이지에 보여질 항목 수
		// 페이징 정보를 생성하여 BookService의 findAll 메서드에 전달
		PageRequest pageable = PageRequest.of(page, size);  
		// BookService의 findAll 메서드 호출하여 페이징된 도서 목록을 가져옴
		Page<Book> bookPage = bookService.findAll(pageable); 

		// Page에서 Content를 추출하여 List로 변환하여 모델에 추가
		// Model에 "allBooks"라는 이름으로 페이징된 도서 목록 추가
		model.addAttribute("allBooks", bookPage.getContent());  // bookPage.getContent() : 현재 페이지의 도서 목록
		return "index";
	}

	@GetMapping("/books/search")
	public String searchBooks(Model model, @RequestParam("term") String searchTerm) {
		List<Book> searchResults = bookService.findBooksByTitleOrWriter(searchTerm);
		model.addAttribute("books", searchResults);
		return "index";
	}

	@GetMapping("/books/bestSellers")
	public String getBestSellers(Model model) {
		List<Book> bestSellers = bookService.findBestSellers();
		model.addAttribute("bestSellers", bestSellers);
		return "index";
	}
	
	@GetMapping("/books/detail")
	public String getBookDetail(Model model, @RequestParam("id") Long id) {
	    // bookService를 통해 도서 상세 정보를 가져옵니다.
	    Optional<Book> bookDetail = bookService.findBookById(id);

	    // 도서 상세 정보를 Model에 추가합니다.
	    model.addAttribute("book", bookDetail.orElse(null));

	    // "bookDetail"이라는 뷰를 반환합니다.
	    return "bookDetail";
	}

}
