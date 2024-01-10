package com.bookrealm.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@DynamicInsert
public class Book {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 30, name = "book_id")
    private Long id;
    
    @Column(nullable = false)
    private Integer price;  // 판매 가격

    @ColumnDefault("10")
    private Integer stock;  // 재고
    
    @ColumnDefault("0")
    private Integer sales;  // 판매수량
    
    private String category;  // 카테고리

    @Column(nullable = false)
    private String writer;  // 저자 이름

    @Column(nullable = false)
    private String title;  // 책 제목

    @Column(nullable = false)
    private String publisher;  // 출판사

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate publishDate;  // 출간일
    
    private String image;  // 섬네일 이미지의 URL

    @Column(unique = true, length = 15)
    private Long isbn;  // ISBN
    
    @Column(columnDefinition = "TEXT")
    private String description;  // 네이버 도서의 책 소개

    @OneToMany(mappedBy = "book")
    private List<Cart> carts = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private List<Favorite> favorites = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private List<OrderList> orderlists = new ArrayList<>();

}