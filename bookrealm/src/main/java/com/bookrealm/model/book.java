package com.bookrealm.model;

import jakarta.persistence.*;
import java.sql.Date;

public class book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 30)
    private Integer ISBN;  //국제표준 도서번호
    @Column(length = 10)
    private Integer discount;  // 판매 가격
    @Column(length = 10)
    private Integer stock;  // 재고
    @Column(length = 10)
    private Integer sales;  // 판매수량
    @Column(length = 10)
    private String category;  // 카테고리
    @Column(length = 10)
    private String author;  // 저자 이름
    @Column(length = 30)
    private String title;  // 책 제목
    @Column(length = 10)
    private String publisher;  // 출판사

    @Temporal(TemporalType.DATE)
    @Column(length = 10)
    private Date pubdate;  // 출간일
    @Column(columnDefinition = "TEXT")
    private String description;  // 네이버 도서의 책 소개

}
