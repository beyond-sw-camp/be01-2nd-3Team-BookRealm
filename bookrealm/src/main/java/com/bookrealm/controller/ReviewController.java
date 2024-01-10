//package com.bookrealm.controller;
//
//import com.bookrealm.service.ReviewService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//public class ReviewController {
//
//    private final ReviewService reviewService;
//
//    @Autowired
//    public ReviewController(ReviewService reviewService) {
//        this.reviewService = reviewService;
//    }
//
//    @GetMapping("/add-review")
//    public String showAddReviewForm() {
//        return "add-review-form";
//    }
//
//    @PostMapping("/add-review")
//    public String addReview(String contents, int popular) {
//        reviewService.addReview(contents, popular);
//        return "redirect:/review-list";
//    }
//
//    @GetMapping("/review-list")
//    public String showReviewList(Model model) {
//        // 리뷰 리스트를 모델에 추가
//        model.addAttribute("reviews", reviewService.getAllReviews());
//        return "review-list";
//    }
//}
