package com.bookrealm.model;

import jakarta.persistence.*;
import java.sql.Date;

public class Book {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 30)
    private Integer bookId;  //국제표준 도서번호
    
    @Column(length = 10)
    private Integer price;  // 판매 가격
    
    @Column(length = 10)
    private Integer stock;  // 재고
    
    @Column(length = 10)
    private Integer sales;  // 판매수량
    
    @Column(length = 10)
    private String category;  // 카테고리
    
    @Column(length = 20)
    private String writer;  // 저자 이름
    
    @Column(length = 30)
    private String title;  // 책 제목
    
    @Column(length = 10)
    private String publisher;  // 출판사

    @Column(length = 10)
    private Date publishDate;  // 출간일
    
    @Column(columnDefinition = "TEXT")
    private String description;  // 네이버 도서의 책 소개

}