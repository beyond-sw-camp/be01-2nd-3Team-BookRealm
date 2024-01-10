package com.bookrealm.model;

import static jakarta.persistence.FetchType.LAZY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_id")
	private Long id; // 장바구니 코드 (PK)

	@Column(nullable = false, columnDefinition = "Integer DEFAULT 1")
	private Integer purchase; // 수량

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "book_id")
	private Book book;

	// user가 처음 장바구니에 책을 담기위해 장바구니 생성
	public static Cart createCart(User user) {
		Cart cart = new Cart();
		cart.setPurchase(0);
		cart.setUser(user);
		return cart;
	}

	// 장바구니에 담을 책 수량 증가시켜주는 메소드
	public static Cart createCartBook(User user, Book book, int purchase) {
		Cart cartBook = new Cart();
		cartBook.setUser(user);
		cartBook.setPurchase(purchase);
		cartBook.setBook(book);
		return cartBook;
	}

	public void addPurchase(int purchase) {
		this.purchase += purchase;
	}
}
