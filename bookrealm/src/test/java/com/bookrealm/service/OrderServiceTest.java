package com.bookrealm.service;

import com.bookrealm.model.*;
import com.bookrealm.repository.BookRepository;
import com.bookrealm.repository.OrderRepository;
import com.bookrealm.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;



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
    public void 구매총액(){

        //given
        Book book = savedBook();
        OrderList orderList1 = new OrderList();
        orderList1.setBook(book);
        orderList1.setPurchase(2);

        List<OrderList> orderLists = new ArrayList<>();
        orderLists.add(orderList1);

        Order order = new Order();
        order.setOrderLists(orderLists);

        // When
        int totalPrice = orderService.totalPrice(order);

        // Then
        assertEquals(24000, totalPrice);

    }
    @Test
    public void 결재수단선택및결재(){
        //given
        Book book = savedBook();
        OrderList orderList1 = new OrderList();
        orderList1.setBook(book);
        orderList1.setPurchase(2);

        List<OrderList> orderLists = new ArrayList<>();
        orderLists.add(orderList1);

        Order order = new Order();
        order.setOrderLists(orderLists);
        int totalPrice = orderService.totalPrice(order);
        Payment payment = Payment.CREDIT_CARD;

        //when
        orderService.selectPayment(order, payment);

        //then
        switch (payment) {
            case CREDIT_CARD:
                orderService.processCreditCardPayment(totalPrice);
                break;

            case BANK_TRANSFER:
                orderService.processBankTransferPayment(totalPrice);
                break;

            default:
                throw new IllegalArgumentException("지원하지 않는 결제수단입니다.");
        }
    }
}
