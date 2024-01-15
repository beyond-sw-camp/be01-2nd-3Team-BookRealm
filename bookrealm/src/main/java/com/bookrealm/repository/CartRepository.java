package com.bookrealm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookrealm.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	
	Optional<Cart> findById(Long id);
	
	// 현재 로그인한 회원 조회
	List<Cart> findByMemberId(Long memberId);

	// 장바구니에 들어갈 상품을 저장하거나 조회
	Optional<Cart> findByIdAndBookId(Long cartId, Long bookId);

	Optional<Cart> findByMemberEmailAndBookId(String username, Long bookId);
	void deleteByIdAndMemberId(Long cartId, Long id);

	Optional<Cart> findByMemberIdAndBookId(Long memberId, Long bookId);

	void deleteByMemberId(Long id);
}
