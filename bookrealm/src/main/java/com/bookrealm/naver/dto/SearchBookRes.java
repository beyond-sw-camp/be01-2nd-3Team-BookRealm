package com.bookrealm.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchBookRes {

    private String lastBuildDate;
    private int total;          // 총 검색 결과 수
    private int start;          // 검색 시작 위치
    private int display;        // 한 번에 표시할 개수

    private List<SearchBookItem> items;     // 검색 결과 도서 리스트

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchBookItem {

        private String title;           // 책 제목
        private String link;            // 네이버 도서 정보 url
        private String image;           // 섬네일 이미지 url
        private String author;          // 저자 이름
        private int discount;       // 판매가격(api 상에 가격이 없으면 반환 x)
        private String publisher;       // 출판사
        private Long isbn;           // ISBN
        private String description;     // 네이버 도서 책 소개
        private String pubDate;           // 출간일

    }

}
