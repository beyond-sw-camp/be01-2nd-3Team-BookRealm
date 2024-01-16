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

        Book bookDetail = bookService.findBookById(id);
        List<Review> reviews = reviewService.findByBookId(id);
        model.addAttribute("book", bookDetail);
        model.addAttribute("reviews", reviews);

        return "book-detail";
    }

    @PostMapping("/detail/add-review")
    public String addReview(Model model, @RequestParam("id") Long bookId,
                            @RequestParam("contents") String contents,
                            @RequestParam("popular") int popular, Principal principal) {

        Book bookDetail = bookService.findBookById(bookId);

        Book book = bookDetail;
        // 리뷰 추가
        reviewService.addReview(memberService.getUser(principal.getName()), book, contents, popular);


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
