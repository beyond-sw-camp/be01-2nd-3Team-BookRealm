package com.bookrealm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bookrealm.model.*;
import com.bookrealm.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		// 이미 장바구니에 있는 도서인지 확인
		Optional<Cart> existingCartItem = cartRepository.findByMemberIdAndBookId(member.getId(), book.getId());

		// 기존에 있는 항목이 있으면 수량만 증가
		if (existingCartItem.isPresent()) {
			Cart cart = existingCartItem.get();
			cart.addPurchase(purchase);
			return cartRepository.save(cart);
		}

		// 없으면 새로운 장바구니 항목 생성
		Cart newCartItem = Cart.createCartBook(member, book, purchase);
		return cartRepository.save(newCartItem);
	}

	public int totalPrice(Member member){  // 총가격 메서드
		int total = 0;
		List<Cart> carts = cartRepository.findByMemberId(member.getId());

		for(Cart cart : carts) {
			Book book = cart.getBook();
			total += book.getPrice() * cart.getPurchase();
		}

		return total;
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
	public List<Cart> getCartByMember(Member member) {
		List<Cart> carts = cartRepository.findByMemberId(member.getId());
		return carts;
	}

	@Transactional 
	public Cart deleteBookFromCart(Member member, Book book){
		Cart cart = cartRepository.findByIdAndBookId(member.getId(),book.getId()).orElse(null);

		if(cart != null){
			cartRepository.delete(cart);
		}

		return cart;
	}

	public void updateCartQuantity(String email, Long bookId, int purchase) {
		Optional<Cart> cartOptional = cartRepository.findByMemberEmailAndBookId(email, bookId);

		if (cartOptional.isPresent()) {
			Cart cart = cartOptional.get();
			cart.setPurchase(purchase);
			cartRepository.save(cart);
		}
	}

}
