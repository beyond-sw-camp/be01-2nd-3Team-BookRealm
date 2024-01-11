package com.bookrealm.controller;

import com.bookrealm.model.Book;
import com.bookrealm.model.Cart;
import com.bookrealm.model.Member;
import com.bookrealm.repository.BookRepository;
import com.bookrealm.service.BookService;
import com.bookrealm.service.CartService;
import com.bookrealm.service.MemberService;

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

@Controller
public class CartController {

    private final CartService cartService;
    private final MemberService memberService;
    private final BookService bookService;
    private final BookRepository bookRepository;

    @Autowired
    public CartController(CartService cartService, MemberService memberService, BookService bookService, BookRepository bookRepository) {
        this.cartService = cartService;
        this.memberService = memberService;
		this.bookService = bookService;
		this.bookRepository = bookRepository;
    }
    
    // 장바구니에 책 추가
    @PostMapping("/cart/add/{bookId}")
    public String addToCart(@PathVariable Long bookId, Principal principal) {
     
            Member member = memberService.getUser(principal.getName());
            Book book = bookRepository.findById(bookId).orElse(null);

            if (book != null) {
                cartService.addCart(member, book, 1);  // 장바구니에 1권 추가 (수량 조절 가능)
            }
        
        return "redirect:/cart/id=" + bookId;
    }
    
    
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/cart")
    public String viewCart(Model model, Principal principal) {
        if (principal != null) {
            Member member = memberService.getUser(principal.getName());
            List<Cart> carts = member.getCarts();

            List<Cart> cartBooks = cartService.memberCartView(carts);

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
    
//    @GetMapping("/user/{id}/cart")
//    public String myCartPage(@PathVariable("id") Long memberId, Model model, @AuthenticationPrincipal Principal principal) {
//        if (member.principal.getName().equals(memberId.toString())) {
//            Member member = memberService.getUser(principal.getName());
//            List<Cart> carts = member.getCarts();
//
//            List<Cart> cartBooks = cartService.memberCartView(carts);
//
//            int totalPrice = 0;
//            for (Cart cartBook : cartBooks) {
//                // 각 카트 아이템에 대한 가격 계산 로직을 추가
//                totalPrice += (cartBook.getBook().getPrice() * cartBook.getQuantity());
//            }
//
//            model.addAttribute("cartBooks", cartBooks);
//            model.addAttribute("totalPrice", totalPrice);
//            model.addAttribute("member", member);
//
//            return "book-cart"; // 원하는 페이지로 수정
//        } else {
//            return "redirect:/";
//        }
//    }
	
//	@PreAuthorize("isAuthenticated()")
//	@PostMapping("/user/{id}/cart")
//	public @ResponseBody ResponseEntity myCartPage(@RequestBody @Valid Cart cart, 
//			BindingResult bindingResult, Principal principal) {
//		// 로그인 User == 접속 User
//		if(bindingResult.hasErrors()) {
//			StringBuilder sb = new StringBuilder();
//			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//            for (FieldError fieldError : fieldErrors) {
//                sb.append(fieldError.getDefaultMessage());
//            }
//            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
//		}
//		
//		String email = principal.getName();
//		Long cartId;
//		
//		try {
//		    Long bookId = cart.getBook();
//		    Book book = bookRepository.findById(bookId).orElse(null);
//
//	        // 장바구니에 0권 추가 (수량 조절 가능)
//	        Cart savedCart = cartService.addCart(member, book, 0);
//
//	        return new ResponseEntity<Long>(savedCart.getId(), HttpStatus.OK);
//	    } catch (Exception e) {
//	        return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
//	    }	
//	}
}
