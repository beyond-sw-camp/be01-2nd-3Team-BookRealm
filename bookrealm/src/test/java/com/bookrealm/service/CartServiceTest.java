package com.bookrealm.service;

import static com.bookrealm.model.SuType.NORMAL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.util.Optional;

import com.bookrealm.model.Member;
import com.bookrealm.repository.MemberRepository;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bookrealm.model.Address;
import com.bookrealm.model.Book;
import com.bookrealm.model.Cart;
import com.bookrealm.repository.BookRepository;
import com.bookrealm.repository.CartRepository;

@SpringBootTest
class CartServiceTest {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
    private CartRepository cartRepository;
	
	@Autowired
    private MemberRepository memberRepository;
	
	@Autowired
    private BookRepository bookRepository;
	
	public Member savedMember() {
    	Member newMember = new Member();
    	newMember.setEmail("bb@naver.com");
    	newMember.setUsername("김단아");
    	newMember.setPasswd("1111");
    	newMember.setAddress(new Address("123","시흥시", "대은로", "62"));
    	newMember.setPhone("01023456789");
    	newMember.setSuType(NORMAL);
    	return memberRepository.save(newMember);
    }
    
    public Book savedBook() {
    	Book newBook = new Book();
    	newBook.setPrice(12000);
    	newBook.setStock(2);
    	newBook.setSales(500);
    	newBook.setCategory("소설");
    	newBook.setWriter("J. K. 롤링");
    	newBook.setTitle("해리 포터와 마법사의 돌 1");
    	newBook.setPublisher("문학수첩");
    	newBook.setPublishDate(LocalDate.of(2019,11,19));
    	newBook.setImage("http://bookthumb.phinf.naver.net/cover/108/346/10834650.jpg?type=m1&udate=20160902");
    	newBook.setDescription("1999년 <해리 포터와 마법사의 돌>의 출간을 필두로 지금까지(2019년 9월 기준) 약 1,500만 부가 판매되었으며, 현재에도 독자들에게 변함없는 사랑을 받고 있다.");
    	return bookRepository.save(newBook);
    }
    
    @Test
    public void 장바구니_담기() {
    	//given
    	Member member = savedMember();
    	Book book = savedBook();
    	
    	// when
    	Cart cart = Cart.createCart(member);
    	Cart savedCart = new Cart();
    	
    	// then
        savedCart = cartService.addCart(member, book, 1);
        assertEquals(member.getId(), savedCart.getMember().getId());
        assertEquals(book.getId(), savedCart.getBook().getId());
//        assertEquals(cart.getPurchase(), savedCart.getPurchase());
    }

	@Test
	public void 장바구니_제거(){

		//given
		Member member = savedMember();
		Book book = savedBook();
		Cart cart = Cart.createCart(member);
		cartService.addCart(member, book, 1);

		//when
		cartService.deleteBookFromCart(member, book);


		//then
		Cart updateCart = cartRepository.findById(member.getId()).orElse(null);
		assertNull(updateCart, "장바구니에서 상품이 올바르게 제거되었는지 확인");

	}

	@Test
	public void 장바구니_수량업데이트(){

		//given
		Member member = savedMember();
		Book book = savedBook();
		cartService.addCart(member,book,1);

		//when
		int purchase = 5;
		cartService.updateCartQuantity(member.getEmail(),book.getId(),purchase);


		//then
		Cart newcart = cartRepository.findByMemberEmailAndBookId(member.getEmail(), book.getId()).orElse(null);
		assertEquals(purchase, newcart.getPurchase());

	}

}