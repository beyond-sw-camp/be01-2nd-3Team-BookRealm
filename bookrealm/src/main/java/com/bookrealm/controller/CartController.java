package com.bookrealm.controller;

import com.bookrealm.model.Book;
import com.bookrealm.model.Cart;
import com.bookrealm.model.Member;
import com.bookrealm.repository.BookRepository;
import com.bookrealm.repository.CartRepository;
import com.bookrealm.service.BookService;
import com.bookrealm.service.CartService;
import com.bookrealm.service.MemberService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
public class CartController {

    private final CartService cartService;
    private final MemberService memberService;
    private final BookService bookService;
    private final BookRepository bookRepository;
    private final CartRepository cartRepository;

    @Autowired
    public CartController(CartService cartService, MemberService memberService, BookService bookService, BookRepository bookRepository, CartRepository cartRepository) {
        this.cartService = cartService;
        this.memberService = memberService;
        this.bookService = bookService;
        this.bookRepository = bookRepository;
        this.cartRepository = cartRepository;
    }

    // 장바구니에 책 추가
        @PostMapping("/cart/add")
        public String addToCart(@RequestParam("bookId") Long bookId, @RequestParam("purchase") int purchase, Principal principal) {

            Member member = memberService.getUser(principal.getName());
            Book book = bookRepository.findById(bookId).orElse(null);

            if (book != null) {
                cartService.addCart(member, book, purchase);  // 장바구니에 1권 추가 (수량 조절 가능)
            }

            return "redirect:/cart/add";
        }

    @GetMapping("/cart/add")
    public String viewCart(Model model, Principal principal) {
        if (principal != null) {
            Member member = memberService.getUser(principal.getName());
            List<Cart> cartBooks = cartRepository.findByMemberId(member.getId());

            int totalPrice = 0;
            for (Cart cartBook : cartBooks) {
                totalPrice += (cartBook.getBook().getPrice() * cartBook.getPurchase());
            }

            model.addAttribute("cartBooks", cartBooks);
            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("member", member);

            return "book-cart";
        } else {
            return "redirect:/";
        }
    }
    
    @PostMapping("/cart/delete")
    @Transactional
    public String removeFromCart(@RequestParam("cartId") Long cartId, Principal principal) {
        if (principal != null) {
            Member member = memberService.getUser(principal.getName());

            // Remove the specific item from the cart
            cartRepository.deleteByIdAndMemberId(cartId, member.getId());

            // Check if the cart is empty after removal
            List<Cart> remainingItems = cartRepository.findByMemberId(member.getId());
            if (remainingItems.isEmpty()) {
                // Redirect to book-detail if the cart is empty
                return "redirect:/cart/add";
            }
        }
        // Redirect to cart page in any other case
        return "redirect:/cart/add";
    }

    @PostMapping("/cart/update")
    public String updateQuantity(@RequestParam("bookId") Long cartId,
                                 @RequestParam("purchase") int purchase,
                                 Principal principal) {
        cartService.updateCartQuantity(principal.getName(), cartId, purchase);
        return "redirect:/cart/add";
    }

}