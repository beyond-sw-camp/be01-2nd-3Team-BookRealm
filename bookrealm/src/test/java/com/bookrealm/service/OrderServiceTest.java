package com.bookrealm.service;

import com.bookrealm.model.*;
import com.bookrealm.repository.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.bookrealm.model.Payment.CREDIT_CARD;
import static com.bookrealm.model.SuType.NORMAL;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class OrderServiceTest {



    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderService orderService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderListRepository orderListRepository;
    @Autowired
    private OrderRepository orderRepository;


    @Transactional
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

    @Transactional
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

    @Transactional
    public Order saveOrder(){
        Order order = new Order();
        order.setId(1L);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(10);
        order.setPayment(CREDIT_CARD);
        return orderRepository.save(order);
    }

    @Transactional
    public OrderList saveOrderList(){
        OrderList orderList = new OrderList();
        orderList.setPurchase(2);
        orderList.setStatus(Status.ORDER_COMPLETE);
        orderList.setBook(savedBook());
        orderList.setOrder(saveOrder());
        return orderListRepository.save(orderList);
    }

    @Test
    public void 구매총액(){

        //given
        OrderList orderList = saveOrderList();

        // when
        int totalPrice = orderService.totalPrice(orderList.getOrder());

        // then
        assertEquals(24000, totalPrice);

    }

    @Test
    public void 결재수단선택및결재(){
        //given
        OrderList orderList = saveOrderList();

        List<OrderList> orderLists = new ArrayList<>();
        orderLists.add(orderList);

        Order order = saveOrder();
        order.setOrderLists(orderLists);

        order.setPayment(Payment.CREDIT_CARD);

        //when
        orderService.selectPayment(order);

        //then
        switch (order.getPayment()) {
            case CREDIT_CARD:
                orderService.processCreditCardPayment(order.getTotalAmount());
                break;

            case BANK_TRANSFER:
                orderService.processBankTransferPayment(order.getTotalAmount());
                break;

            default:
                throw new IllegalArgumentException("지원하지 않는 결제수단입니다.");
        }
    }

    @Test
    public void 구매() {
        //given
        Order order = new Order();
        order.setPayment(CREDIT_CARD);
        Address address = new Address("1234","서울시", "갈현로4길", "25-9");
        order.setDestination(address);
        Member member = savedMember();
        order.setMember(member);

        Book book = savedBook();
        Cart cart = new Cart();
        cart.setMember(member);
        cart.setPurchase(2);
        cart.setBook(book);
        cartRepository.save(cart);

        //when
        order = orderService.cartOrder(order);

        //then
        List<OrderList> orderList = orderListRepository.findByOrderId(order.getId());
        assertEquals(orderList.get(0).getBook(), book);
    }
}
