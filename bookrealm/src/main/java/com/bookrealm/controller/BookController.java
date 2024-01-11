package com.bookrealm.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import com.bookrealm.model.Member;
import com.bookrealm.model.Review;
import com.bookrealm.service.MemberService;
import com.bookrealm.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bookrealm.model.Book;
import com.bookrealm.service.BookService;

@RequestMapping("/")
@Controller
public class BookController {

    private final BookService bookService;
    private final ReviewService reviewService;

    private final MemberService memberService;

    @Autowired
    public BookController(BookService bookService, ReviewService reviewService, MemberService memberService) {
        this.bookService = bookService;
        this.reviewService = reviewService;
        this.memberService = memberService;
    }

//    한 페이지에 리스트 출력
//    @GetMapping("/books")
//    public String findAll(Model model) {
//        List<Book> allBooks = bookService.findAll();
//        model.addAttribute("allBooks", allBooks);
//        return "index";  
//    }

    @GetMapping("/all")
    public String getAll(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                         @RequestParam(name = "size", defaultValue = "20") int size) {  // page : 현재 페이지 번호, size : 한 페이지에 보여질 항목 수
        // 페이징 정보를 생성하여 BookService의 findAll 메서드에 전달
        PageRequest pageable = PageRequest.of(page, size);
        // BookService의 findAll 메서드 호출하여 페이징된 도서 목록을 가져옴
        Page<Book> bookPage = bookService.findAll(pageable);

        // Page에서 Content를 추출하여 List로 변환하여 모델에 추가
        // Model에 "allBooks"라는 이름으로 페이징된 도서 목록 추가
        model.addAttribute("allBooks", bookPage.getContent());  // bookPage.getContent() : 현재 페이지의 도서 목록
        return "book-all";
    }

    @GetMapping("/bookSearch")
    public String searchBook(Model model, @RequestParam("term") String searchTerm) {

        List<Book> searchResults = bookService.findBooksByTitleOrWriter(searchTerm);
        model.addAttribute("searchBook", searchResults);

        return "book-search";

    }

    @GetMapping
    public String getBestSellers(Model model) {

        List<Book> bestSellers = bookService.findBestSellers();
        model.addAttribute("bestSellers", bestSellers);

        return "index";
    }

    @GetMapping("/detail")
    public String getBookDetail(Model model, @RequestParam("id") Long id) {

        Optional<Book> bookDetail = bookService.findBookById(id);
        List<Review> reviews = reviewService.findByBookId(id);
        model.addAttribute("book", bookDetail.orElse(null));
        model.addAttribute("reviews", reviews);

        return "book-detail";
    }

    @PostMapping("/detail/add-review")
    public String addReview(Model model, @RequestParam("id") Long bookId,
                            @RequestParam("contents") String contents,
                            @RequestParam("popular") int popular, Principal principal) {

        Optional<Book> bookDetail = bookService.findBookById(bookId);
        if (bookDetail.isPresent()) {
            Book book = bookDetail.get();
            // 리뷰 추가
            reviewService.addReview(memberService.getUser(principal.getName()), book, contents, popular);
        }

        // 도서 상세 페이지로 리다이렉트
        return "redirect:/detail?id=" + bookId;
    }

    @PostMapping("/detail/edit-review")
    public String editReview(@RequestParam("reviewId") Long reviewId,
                             @RequestParam("contents") String contents,
                             @RequestParam("popular") int popular) {
        // 리뷰 수정 로직 수행
        reviewService.updateReview(reviewId, contents, popular);

        // 도서 상세 페이지로 리다이렉트
        return "redirect:/detail?id=" + reviewService.findById(reviewId).getBook().getId();
    }

    @PostMapping("/detail/delete-review")
    public String deleteReview(@RequestParam("reviewId") Long reviewId,@RequestParam("id") Long bookId) {
        // 리뷰 삭제 로직 수행

        reviewService.deleteReview(reviewId);

        // 도서 상세 페이지로 리다이렉트 또는 다른 적절한 처리 수행
        return "redirect:/detail?id="+ bookId;
    }
}

