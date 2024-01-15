package com.bookrealm.controller;

import com.bookrealm.model.Address;
import com.bookrealm.model.Member;
import com.bookrealm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/myPage")
public class UserController {

    private final MemberService memberService;
    private final ReviewService reviewService;
    private final OrderService orderService;

    @Autowired
    public UserController(MemberService memberService, ReviewService reviewService, OrderService orderService) {
        this.memberService = memberService;
        this.reviewService = reviewService;
        this.orderService = orderService;
    }

    @GetMapping
    public String myPage(Model model, Principal principal) {
        model.addAttribute("member", memberService.getUser(principal.getName()));

        return "myPage";
    }

    @GetMapping("/address")
    public String addressMod(Model model, Principal principal) {
        model.addAttribute("address", memberService.getUser(principal.getName()).getAddress());
        return "addressMod";
    }

    @PostMapping("/changeAddress")
    public String changeAddress(Model model, Address address, Principal principal) {
        memberService.addressMod(principal.getName(), address);
        return "redirect:/myPage";
    }

    @GetMapping("/phone")
    public String phoneMod(Model model, Principal principal) {
        return "phoneMod";
    }

    @PostMapping("/changePhone")
    public String changePhone(Model model, String phoneNumber, Principal principal) {
        memberService.phoneMod(principal.getName(), phoneNumber);
        return "redirect:/myPage";
    }

    @PostMapping("/changePassword")
    public String changePassword(Model model,String currentPassword, String newPassword, Principal principal) {

        memberService.passwordMod(principal.getName(), currentPassword, newPassword);
        return "redirect:/myPage";
    }

    @GetMapping("/myReview")
    public String myReview(Model model, Principal principal) {
        model.addAttribute("reviews", reviewService.findByMemberId(memberService.getUserReturnId(principal.getName())));

        return "myReviews";
    }

    @GetMapping("/myOrders")
    public String myOrder(Model model, Principal principal) {
        Member member = memberService.getUser(principal.getName());
        model.addAttribute("user", member);

        return "myOrders";
    }

}
