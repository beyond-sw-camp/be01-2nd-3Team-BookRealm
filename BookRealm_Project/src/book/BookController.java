package book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;

import ConnUtil.CloseHelper;
import ConnUtil.ConnectionSingletonHelper;

public class BookController {
	
	 //연결, 삽입, 삭제, 수정, 검색....
    static Scanner sc = new Scanner(System.in);
    static Statement  stmt = null;
    static ResultSet  rs = null;
    static Connection conn = null;
    static PreparedStatement  pstmt = null;
    
    // connect
    public static void connect() {
        try {
            conn = ConnectionSingletonHelper.getConnection("mariadb");
            stmt = conn.createStatement();
            conn.setAutoCommit(false);
        } catch (Exception e) {  e.printStackTrace();  }
    }
    
    // close
    public static void close() {
        try {
            CloseHelper.close(rs);			CloseHelper.close(stmt);			CloseHelper.close(pstmt);			CloseHelper.close(conn);
        } catch (Exception e) {  e.printStackTrace();  }
    }
    
 // menu
    public static void menu() throws SQLException {  // 예외처리 위임
        BookModel bm = new BookModel();
        while( true ) {
            System.out.println();

            ConnectionSingletonHelper.menu();
            switch ( sc.nextInt() ) {
                case 0 : System.out.println("Commit 하시겠습니까?(Y/N) ");
                    System.out.println("안하시려면 Rollback 됩니다. ");
                    if( sc.next().equalsIgnoreCase("Y") ) {
                        conn.commit();  // 예외발생
                        selectAll(bm.getClassName());
                    } else {
                        conn.rollback();
                        selectAll(bm.getClassName());
                    }
                    break;

                case 1:
                    selectAll(bm.getClassName());
                    insert();
                    selectAll(bm.getClassName());
                    break;
                case 2:	selectAll(bm.getClassName());
                    update(bm.getClassName());
                    selectAll(bm.getClassName());
                    break;
                case 3:	selectAll(bm.getClassName());
                    break;
                case 4:	selectBybookId(bm.getClassName());
                    break;
                case 5:
                    selectAll(bm.getClassName());
                    delete(bm.getClassName());
                    break;
                case 6:	close();
                    System.out.println("프로그램 종료합니다.!!");
                    System.exit(0);
                    break;
                case 9 :  conn.commit();
                    System.out.println("성공적으로 완료 되었습니다.");
                    break;

                default: System.out.println("없는 번호 선택 하셨습니다. 0~6, 9번 중에서 선택하세요.");
                    break;
            } // end switch

        } // end while
    } // end menu
    
 // delete
    public static void delete(String className) throws SQLException {
        System.out.print("delete bookId = ");
        int bookId = sc.nextInt();
        try {
            pstmt = conn.prepareStatement("DELETE FROM " + className + " WHERE BOOKID = ?" );
            pstmt.setInt(1, bookId);
            int result = pstmt.executeUpdate();  // 쿼리 실행

            System.out.println("정말 삭제하시겠습니까? (y/n) ");
            if( sc.next().equalsIgnoreCase("Y") ) {
                commit();   //conn.commit();  // 예외발생
                System.out.println(result + " 개의 데이터가 삭제 되었습니다. ");
            } else {
                rollback();  //conn.rollback();
                System.out.println("rollback 되었습니다.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // select ~ where
    private static void selectBybookId(String className) throws SQLException {  //
        System.out.println("검색 원하는 도서번호? ");
        pstmt = conn.prepareStatement("select * from "+ className + " where bookId = ? ");
        pstmt.setInt(1, sc.nextInt());
        rs = pstmt.executeQuery();  // 반환값 있는 경우

        ResultSetMetaData rsmd = rs.getMetaData();
        int count = rsmd.getColumnCount();

        while( rs.next() ) {
            for (int i = 1; i <= count; i++) {  // 각 타입별로 출력하기

                switch (rsmd.getColumnType(i)) {
                    case Types.NUMERIC :
                    case Types.INTEGER :
                        System.out.println(rsmd.getColumnName(i) + " : " +rs.getInt(i) + "  ");
                        break;
                    case Types.FLOAT :
                        System.out.println(rsmd.getColumnName(i) + " : " +rs.getFloat(i) + "  ");
                        break;
                    case Types.DOUBLE :
                        System.out.println(rsmd.getColumnName(i) + " : " +rs.getDouble(i) + "  ");
                        break;
                    case Types.CHAR :
                        System.out.println(rsmd.getColumnName(i) + " : " +rs.getString(i) + "  ");
                        break;
                    case Types.DATE :
                        System.out.println(rsmd.getColumnName(i) + " : " +rs.getDate(i) + "  ");
                        break;
                    default:
                        System.out.println(rsmd.getColumnName(i) + " : " +rs.getString(i) + "  ");
                        break;
                } // switch end
            } // end for
            System.out.println();
        } // end while
    } // end selectBybookId

    // rollback
    public static void rollback() throws SQLException {
        conn.rollback();
        System.out.println("rollback success!!!");
    }
    // commit
    public static void commit() throws SQLException {
        conn.commit();
        System.out.println("commit success!!");
    }

    // update
    public static void update(String className) throws SQLException {  //

        a:
        while(true) {
            selectAll(className);
            String modify = null;

            System.out.println("0 선택 ==>  update  탈출합니다. ");
            System.out.print("\n\n수정할 bookId : ");
            int bookId = sc.nextInt();

            b:
            while(true) {
                System.out.println("\n\n1.bookId\t2.category\t3.writer\t4.title\t5.price\t6.stock"
                		+ "			\t7.sales\t8.publishDate\t9.publisher\t0.exit");
                System.out.print("수정할 필드 : ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1 : System.out.print("바뀔 bookId : ");
                        stmt.execute("update " + className + " set bookId = " + sc.nextInt() + " where bookId = " + bookId);
                        break b;
                    case 2 : System.out.print("바뀔 category : ");
                        stmt.execute("update " + className + " set category = '" + sc.next() + "' where bookId = " + bookId);
                        break b;
                    case 3 : System.out.print("바뀔 writer : ");
                        stmt.execute("update " + className + " set writer = " + sc.next() + " where bookId = " + bookId);
                        break b;
                    case 4 : System.out.print("바뀔 title : ");
                        stmt.execute("update " + className + " set title = " + sc.next() + " where bookId = " + bookId);
                        break b;
                    case 5 : System.out.print("바뀔 price : ");
                    stmt.execute("update " + className + " set price = " + sc.nextInt() + " where bookId = " + bookId);
                    break b;
                    case 6 : System.out.print("바뀔 stock : ");
                    stmt.execute("update " + className + " set stock = " + sc.nextInt() + " where bookId = " + bookId);
                    break b;
                    case 7 : System.out.print("바뀔 sales : ");
                    stmt.execute("update " + className + " set sales = " + sc.nextInt() + " where bookId = " + bookId);
                    break b;
                    case 8 : System.out.print("바뀔 publishDate : ");
                    stmt.execute("update " + className + " set publishDate = '" + sc.next() + "' where bookId = " + bookId);
                    break b;
                    case 9 : System.out.print("바뀔 publisher : ");
                    stmt.execute("update " + className + " set publisher = '" + sc.next() + "' where bookId = " + bookId);
                    break b;  // in while break;
                    
                    case 0 :   break a;   // out while break;

                    default:  System.out.println("잘못된 번호 입니다. ");
                        break;
                } // end switch

                switch( choice ) {
                    case 1 : modify = "bookId";
                    case 2 : modify = "category";
                    case 3 : modify = "writer";
                    case 4 : modify = "title";
                    case 5 : modify = "price";
                    case 6 : modify = "stock";
                    case 7 : modify = "sales";
                    case 8 : modify = "publishDate";
                    case 9 : modify = "publisher";
                }

                System.out.println(modify + " 수정이 완료 되었습니다. ");

            } // while in end

        } // while out end

    }

    // insert
    public static void insert() {
        System.out.print("bookId : ");	    	int bookId = sc.nextInt();
        System.out.print("category : ");		String category = sc.next();
        System.out.print("writer : ");			String writer = sc.next();
        System.out.print("title : ");			String title = sc.next();
        System.out.print("price : ");	    	int price = sc.nextInt();
        System.out.print("stock : ");	    	int stock = sc.nextInt();
        System.out.print("sales : ");	    	int sales = sc.nextInt();
        System.out.print("publishDate : ");		String publishDate = sc.next();
        System.out.print("publisher : ");		String publisher = sc.next();

        try {
            pstmt = conn.prepareStatement("INSERT INTO BOOK VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ? )");
            pstmt.setInt(1, bookId);
            pstmt.setString(2, category);
            pstmt.setString(3, writer);
            pstmt.setString(4, title);
            pstmt.setInt(5, price);
            pstmt.setInt(6, stock);
            pstmt.setInt(7, sales);
            pstmt.setString(8, publishDate);
            pstmt.setString(9, publisher);
            int result = pstmt.executeUpdate();

            System.out.println(result + "개 데이터가 추가 되었습니다.");

        } catch (Exception e) {			e.printStackTrace();		}
    }

    // select all
    public static void selectAll(String className) throws SQLException {

        rs = stmt.executeQuery("SELECT * FROM " + className);  // 반환값 있는 경우

        ResultSetMetaData rsmd = rs.getMetaData();
        int count = rsmd.getColumnCount();  //4

        while( rs.next() ) {
            for (int i = 1; i <= count; i++) {  // 각 타입별로 출력하기

                switch (rsmd.getColumnType(i)) {
                    case Types.NUMERIC :
                    case Types.INTEGER :
                        System.out.println(rsmd.getColumnName(i) + " : " + rs.getInt(i) + "  ");
                        break;
                    case Types.FLOAT :
                        System.out.println(rsmd.getColumnName(i) + " : " +rs.getFloat(i) + "  ");
                        break;
                    case Types.DOUBLE :
                        System.out.println(rsmd.getColumnName(i) + " : " + (int)rs.getDouble(i) + "  ");
                        break;
                    case Types.CHAR :
                        System.out.println(rsmd.getColumnName(i) + " : " +rs.getString(i) + "  ");
                        break;
                    case Types.DATE :
                        System.out.println(rsmd.getColumnName(i) + " : " +rs.getDate(i) + "  ");
                        break;
                    default:
                        System.out.println(rsmd.getColumnName(i) + " : " +rs.getString(i) + "  ");
                        break;
                } // switch end
            } // end for
            System.out.println();
        } // end while
    }

}
