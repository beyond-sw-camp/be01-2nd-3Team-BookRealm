package com.bookrealm.controller;

import com.bookrealm.model.Book;
import com.bookrealm.model.User;
import com.bookrealm.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/add-review")
    public String showAddReviewForm(Model model) {
        model.addAttribute("user",new User());
        model.addAttribute("book",new Book());
        return "add-review-form";
    }

    @PostMapping("/add-review")
    public String addReview(@ModelAttribute("user") User user,
                            @ModelAttribute("book") Book book,
                            String contents,
                            int popular) {
        reviewService.addReview(user, book, contents, popular);
        return "redirect:/review-list";
    }

    @GetMapping("/review-list")
    public String showReviewList(Model model) {
        // 리뷰 리스트를 모델에 추가
        model.addAttribute("reviews", reviewService.getAllReviews());
        return "review-list";
    }
}
