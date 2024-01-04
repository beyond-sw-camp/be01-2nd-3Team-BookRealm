package com.bookrealm.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Data
public class Book {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 30, name = "book_id")
    private Long id;  //국제표준 도서번호
    
    @Column(length = 10, nullable = false)
    private Integer price;  // 판매 가격
    
    @Column(length = 10)
    private Integer stock;  // 재고
    
    @Column(length = 10)
    @ColumnDefault("0")
    private Integer sales;  // 판매수량
    
    @Column(length = 10)
    private String category;  // 카테고리

    @Column(nullable = false)
    private String writer;  // 저자 이름

    @Column(nullable = false)
    private String title;  // 책 제목

    @Column(nullable = false)
    private String publisher;  // 출판사

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDateTime publishDate;  // 출간일
    
    @Column(columnDefinition = "TEXT")
    private String description;  // 네이버 도서의 책 소개

}