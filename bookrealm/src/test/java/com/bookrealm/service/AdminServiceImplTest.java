package com.bookrealm.service;

import com.bookrealm.model.*;
import com.bookrealm.naver.NaverBookClient;
import com.bookrealm.naver.dto.SearchBookReq;
import com.bookrealm.naver.dto.SearchBookRes;
import com.bookrealm.repository.OrderRepository;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminServiceImplTest {

    @Autowired
    AdminService adminService;

    @Autowired
    BookService bookService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private NaverBookClient naverBookClient;

    List<Book> books;
    @BeforeEach
    void 검색결과() {
        books = new ArrayList<>();

        var search = new SearchBookReq();
        search.setQuery("네이버");
        SearchBookRes result = naverBookClient.searchBookApi(search);

        StringBuilder sb;
        for(SearchBookRes.SearchBookItem item : result.getItems()){
            Book book = new Book();
            book.setTitle(item.getTitle());
            book.setPrice(item.getDiscount());
            book.setImage(item.getImage());
            book.setWriter(item.getAuthor());
            book.setPublisher(item.getPublisher());
            book.setIsbn(item.getIsbn());
            book.setDescription(item.getDescription());
            sb = new StringBuilder(item.getPubdate());
            sb.insert(4,'-').insert(7,'-');
            book.setPublishDate(LocalDate.parse(sb));

            books.add(book);
        }
    }
    @Test
    @Transactional
    void SAVEALL_도서검색결과저장() {

        adminService.saveAll(books);
        PageRequest pageable = PageRequest.of(0, 20);
        Page<Book> pageBook = bookService.findAll(pageable);
        //System.out.println(pageBook.getContent());
        assertNotNull(pageBook);
    }

    /*@Test
    @Transactional
    void 고객번호_주문전체조회() {

        Member member = new Member();
        Address address = new Address("","","","");
        member.setId(5L);
        member.setAddress(address);

        Order order1 = new Order();
        order1.setId(1L);
        order1.setMember(member);

        Order order2 = new Order();
        order2.setId(2L);
        order2.setMember(member);

        Order order3 = new Order();
        order3.setId(3L);
        order3.setMember(member);

        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);

        List<Order> orderDtos = adminService.findOrderByMemberId(5L);
        System.out.println(orderDtos.size());
    }*/
}