package cart;

import ConnUtil.CloseHelper;
import ConnUtil.ConnectionSingletonHelper;

import java.sql.*;
import java.util.Scanner;

public class CartController {
    static Scanner sc = new Scanner(System.in);
    static Statement stmt = null;
    static ResultSet rs = null;
    static Connection conn = null;
    static PreparedStatement pstmt = null;

    // connect
    public static void connect() {
        try {
            conn = ConnectionSingletonHelper.getConnection("mariadb");
//            conn = ConnectionSingletonHelper.getConnection("mariadb");
            stmt = conn.createStatement();
            conn.setAutoCommit(false);   // 자동커밋 끄기
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
        CartModel  cd = new CartModel();
        while( true ) {
            System.out.println();

            ConnectionSingletonHelper.menu();
            switch ( sc.nextInt() ) {
                case 0 : System.out.println("Commit 하시겠습니까?(Y/N) ");
                    System.out.println("안하시려면 Rollback 됩니다. ");
                    if( sc.next().equalsIgnoreCase("Y") ) {
                        conn.commit();  // 예외발생
                        selectAll(cd.getClassName());
                    } else {
                        conn.rollback();
                        selectAll(cd.getClassName());
                    }
                    break;

                case 1:
                    selectAll(cd.getClassName());
                    insert();
                    selectAll(cd.getClassName());
                    break;
                case 2:	selectAll(cd.getClassName());
                    update(cd.getClassName());
                    selectAll(cd.getClassName());
                    break;
                case 3:	selectAll(cd.getClassName());
                    break;
                case 4:	selectByGno(cd.getClassName());
                    break;
                case 5:
                    selectAll(cd.getClassName());
                    delete(cd.getClassName());
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
        System.out.print("delete recode number carId= ");
        String carId = sc.next();
        try {
            pstmt = conn.prepareStatement("DELETE " + className + " WHERE carId = ?" );
            pstmt.setString(1, carId);
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
    private static void selectByGno(String className) throws SQLException {  //
        System.out.println("검색 원하는 상품번호? ");
        pstmt = conn.prepareStatement("select * from "+ className + " where carId = ? ");
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
    } // end selectByGno

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
            System.out.print("\n\n수정할 carId : ");
            int carId = sc.nextInt();

            b:
            while(true) {
                System.out.println("수정할 필드 : ");
                System.out.println("\n\n1.purchase\t2.bookId\t3.userId\t0.exit");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1 : System.out.print("바뀔 purchase : ");
                        stmt.execute("update " + className + " set purchase = " + sc.nextInt() + " where carId = " + carId);
                        break b;
                    case 2 : System.out.print("바뀔 bookId : ");
                        Object sql = stmt.execute("update " + className + " set bookId = '" + sc.next() + "' where carId = " + carId);
                        System.out.println(sql);
                        break b;
                    case 3 : System.out.print("바뀔 userId : ");
                        stmt.execute("update " + className + " set userId = " + sc.nextInt() + " where carId = " + carId);
                        break b;// in while break;

                    case 0 :   break a;   // out while break;

                    default:  System.out.println("잘못된 번호 입니다. ");
                        break;
                } // end switch

                switch( choice ) {
                    case 1 : modify = "puerchase";
                    case 2 : modify = "bookId";
                    case 3 : modify = "userId";
                }

                System.out.println(modify + " 수정이 완료 되었습니다. ");

            } // while in end

        } // while out end

    }

    // insert
    public static void insert() {
        System.out.print("carId : ");			int carId = sc.nextInt();
        System.out.print("purchase : ");		int purchase = sc.nextInt();
        System.out.print("bookId : ");		int bookId = sc.nextInt();
        System.out.print("userId : ");		String userId = sc.next();

        try {
            pstmt = conn.prepareStatement("INSERT INTO GIFT VALUES( ?, ?, ?, ? )");
            pstmt.setInt(1, carId);
            pstmt.setInt(2, purchase);
            pstmt.setInt(3, bookId);
            pstmt.setString(4, userId);
            int result = pstmt.executeUpdate();

            System.out.println(result + "개 데이터가 추가 되었습니다.");

        } catch (Exception e) {			e.printStackTrace();		}
    }

    // select all
    public static void selectAll(String className) throws SQLException {

        rs = stmt.executeQuery("SELECT * FROM " + className);  // 반환값 있는 경우

        ResultSetMetaData rsmd = rs.getMetaData();
        int count = rsmd.getColumnCount();

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
                    case Types.VARCHAR:
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

