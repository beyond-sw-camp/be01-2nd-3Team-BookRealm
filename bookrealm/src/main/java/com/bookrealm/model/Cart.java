package com.bookrealm.model;

import jakarta.persistence.*;
import lombok.Data;

import static jakarta.persistence.FetchType.LAZY;

@Data
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;  // 장바구니 코드 (PK)

	@Column(nullable = false, columnDefinition = "Integer DEFAULT 1")
	private Integer purchase; // 수량

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "book_id")
	private Book book;

	// user가 처음 장바구니에 책을 담기위해 장바구니 생성
	public static Cart createCart(Member member) {
		Cart cart = new Cart();
		cart.setPurchase(0);
		cart.setMember(member);
		return cart;
	}

	// 장바구니에 담을 책 수량 증가시켜주는 메소드
	public static Cart createCartBook(Member member, Book book, int purchase) {
		Cart cartBook = new Cart();
		cartBook.setMember(member);
		cartBook.setPurchase(purchase);
		cartBook.setBook(book);
		return cartBook;
	}

	public void addPurchase(int purchase) {
		this.purchase += purchase;
	}
}
