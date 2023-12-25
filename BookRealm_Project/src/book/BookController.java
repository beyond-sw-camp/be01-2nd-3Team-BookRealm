package book;

import java.awt.print.Book;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class BookController {

    Scanner sc = new Scanner(System.in);

    private BookDAO bookDAO = new BookDAO();

    /**
     * 관리자 도서 관리 기능
     */

    // 도서 추가
    public void add() throws SQLException {
        BookVO vo = new BookVO();
        System.out.print("도서 제목 입력 : ");       vo.setTitle(sc.next());
        System.out.print("작가 입력 : ");           vo.setWriter(sc.next());
        System.out.print("카테고리 입력 : ");        vo.setCategory(sc.next());
        System.out.print("출판사 입력 : ");          vo.setPublisher(sc.next());
        System.out.print("입고된 재고 수 : ");        vo.setStock(sc.nextInt());
        try{
            System.out.print("도서 발행일자 입력(yyyy-mm-dd) : ");    vo.setPublishDate(Date.valueOf(sc.next()));
        }
        catch(Exception e){
            System.out.println("형식에 맞게 입력해 주세요.");
            System.out.println("--------------------------------------");
            return;
        }
        System.out.print("도서 가격 입력 : ");       vo.setPrice(sc.nextInt());

        if(bookDAO.insertBook(vo) > 0){
            System.out.println("도서 추가가 완료되었습니다.");
        }
        else{
            System.out.println("도서 추가에 실패하였습니다.");
        }
        System.out.println("--------------------------------------");
    }

    // 도서 삭제
    public void delete() throws SQLException {
        System.out.print("삭제할 도서코드를 입력해 주세요 : ");
        int bookId = 0;
        try{
            bookId = sc.nextInt();
        }
        catch(Exception e){
            System.out.println("입력할 수 없는 도서코드 형식입니다.");
            return;
        }

        if(bookDAO.deleteBook(bookId) > 0) {
            System.out.println("도서 정보 삭제가 완료되었습니다.");
        }
        else{
            System.out.println("존재하지 않는 도서코드입니다.");
        }

    }

    // 도서 정보 수정
    public void edit() throws SQLException {
        System.out.print("수정할 도서코드를 입력해 주세요 : ");
        int bookId = 0;
        try{
            bookId = sc.nextInt();
        }
        catch(Exception e){
            System.out.println("입력할 수 없는 도서코드 형식입니다.");
            return;
        }

        BookVO result = bookDAO.selectBybookId(bookId);

        BookVO vo = new BookVO();
        vo.setBookId(bookId);
        System.out.print("도서 제목 입력[" + result.getTitle() + "] : ");       vo.setTitle(sc.next());
        System.out.print("작가 입력[" + result.getWriter() + "] : ");           vo.setWriter(sc.next());
        System.out.print("카테고리 입력[" + result.getCategory() + "] : ");      vo.setCategory(sc.next());
        System.out.print("출판사 입력[" + result.getPublisher() + "] : ");       vo.setPublisher(sc.next());
        System.out.print("입고된 재고 수[" + result.getStock() + "] : ");        vo.setStock(sc.nextInt());
        try{
            System.out.print("도서 발행일자 입력(yyyy-mm-dd) [" + result.getPublishDate() + "] : ");    vo.setPublishDate(Date.valueOf(sc.next()));
        }
        catch(Exception e){
            System.out.println("형식에 맞게 입력해 주세요.");
            System.out.println("--------------------------------------");
            return;
        }
        System.out.print("도서 가격 입력[" + result.getPrice() + "] : ");       vo.setPrice(sc.nextInt());

        if(bookDAO.updateBook(vo) > 0){
            System.out.println("정보 수정이 완료되었습니다.");
        }
        else{
            System.out.println("정보 수정에 실패하였습니다.");
        }

        System.out.println("--------------------------------------");

    }

}
