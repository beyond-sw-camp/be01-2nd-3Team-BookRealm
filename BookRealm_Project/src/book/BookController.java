package book;

import java.awt.print.Book;
import java.util.Scanner;

public class BookController {

    Scanner sc = new Scanner(System.in);

    private BookDAO bookDAO = new BookDAO();

    /**
     * 관리자 도서 관리 기능
     */

    // 도서 추가
    public void add(){
        BookVO vo = new BookVO();
        System.out.print("도서 제목 입력 : ");      vo.setTitle(sc.next());
        System.out.print("작가 입력 : ");           vo.setWriter(sc.next());
        System.out.print("카테고리 입력 : ");        vo.setCategory(sc.next());
        System.out.print("출판사 입력 : ");      vo.setTitle(sc.next());
        System.out.print("입고된 재고 수 : ");      vo.setTitle(sc.next());
        System.out.print("도서 제목");      vo.setTitle(sc.next());
        System.out.print("도서 제목");      vo.setTitle(sc.next());
        System.out.print("도서 제목");      vo.setTitle(sc.next());
    }

    // 도서 삭제
    public void delete(int bookId){

    }

    // 도서 정보 수정
    public void edit(int bookId){

    }

    // 도서 정보 갱신
    public void update(BookVO vo){

    }

}
