package com.bookrealm.service;

import static com.bookrealm.model.SuType.NORMAL;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bookrealm.model.Address;
import com.bookrealm.model.Book;
import com.bookrealm.model.Cart;
import com.bookrealm.model.User;
import com.bookrealm.repository.BookRepository;
import com.bookrealm.repository.CartRepository;
import com.bookrealm.repository.UserRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
class CartServiceTest {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
    private CartRepository cartRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private BookRepository bookRepository;
	
	public User savedUser() {
    	User newUser = new User();
    	newUser.setEmail("bb@naver.com");
    	newUser.setUsername("김단아");
    	newUser.setPasswd("1111");
    	newUser.setAddress(new Address("시흥시", "대은로", "62"));
    	newUser.setPhone("01023456789");
    	newUser.setSuType(NORMAL);
    	return userRepository.save(newUser);
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
    	User user = savedUser();
    	Book book = savedBook();
    	
    	// when
    	Cart cart = Cart.createCart(user);
    	Cart savedCart = new Cart();
    	
    	// then
        savedCart = cartService.addCart(user, book, 1);
        assertEquals(user.getId(), savedCart.getUser().getId());
        assertEquals(book.getId(), savedCart.getBook().getId());
        assertEquals(cart.getPurchase(), savedCart.getPurchase());
    }

}