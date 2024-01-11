package com.bookrealm.service;

import java.util.ArrayList;
import java.util.List;

import com.bookrealm.model.Member;
import com.bookrealm.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookrealm.model.Book;
import com.bookrealm.model.Cart;
import com.bookrealm.repository.BookRepository;
import com.bookrealm.repository.CartRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
	
	private final BookRepository bookRepository;
	private final MemberRepository memberRepository;
	@Autowired
	private final CartRepository cartRepository;

	// User에게 장바구니 생성
	@Transactional 
	public void createCart(Member member) {
		Cart cart = Cart.createCart(member);
		cartRepository.save(cart);
	}
	
	// 장바구니에 book 추가
	@Transactional 
	public Cart addCart(Member member, Book book, int purchase) {
		
		Cart cart = cartRepository.findByIdAndBookId(member.getId(), book.getId()).orElse(null);
		
		// 상품이 장바구니에 존재하지 않는다면 카트상품 생성
		if (cart == null) {
			cart = Cart.createCartBook(member, book, purchase);
		}
		
		// 상품이 장바구니에 이미 존재한다면 수량만 증가
		else {
			cart.addPurchase(purchase);
		}
		return cartRepository.save(cart);
	}
	
	// 장바구니 조회하기
	@Transactional  // 읽기 전용 트랜잭션
	public List<Cart> memberCartView(Cart cart) {
		List<Cart> cart_books = cartRepository.findAll();
		List<Cart> member_books = new ArrayList<>();
		
		for (Cart cart_book : cart_books) {
            if (cart_book.getId() == cart.getId()) {
            	member_books.add(cart_book);
            }
        }

        return member_books;
	}

	@Transactional 
	public Cart deleteBookFromCart(Member member, Book book){
		Cart cart = cartRepository.findByIdAndBookId(member.getId(),book.getId()).orElse(null);

		if(cart != null){
			cartRepository.delete(cart);
		}

		return cart;
	}

	public List<Cart> findByMemberId(Long memberID){
		return cartRepository.findByMemberId(memberID);
	}

}
