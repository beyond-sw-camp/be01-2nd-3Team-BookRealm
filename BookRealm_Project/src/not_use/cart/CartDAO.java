package not_use.cart;

import ConnUtil.CloseHelper;
import ConnUtil.ConnectionSingletonHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {

    //전체출력
    public List<CartVO> selectAll(){
        List<CartVO> list = new ArrayList<CartVO>();

        //1. driver연결
        //2. 계정연결

        Connection conn = ConnectionSingletonHelper.getConnection("mariadb");
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT CARTID, USERID, BOOKID, PURCHASE FROM CART";

        try{
            //3. query 준비
            stmt = conn.createStatement();
            //실행 및 리턴
            rs = stmt.executeQuery(sql);

            while (rs.next()){
                CartVO cvo = new CartVO();
                cvo.setcartId(rs.getInt("cartId"));
                cvo.setUserId(rs.getString("userId"));
                cvo.setBookId(rs.getInt("bookId"));
                cvo.setPurchase(rs.getInt("puerchase"));

                list.add(cvo);
            }

        }catch (SQLException e){
            e.printStackTrace();

        }finally {
            //5.db 종료
            CloseHelper.close(rs);
            CloseHelper.close(stmt);
            CloseHelper.close(conn);
        }
        return list;
    }

    //사용자에 대한 장바구니 출력
    public CartVO selectByUserId(String str){
        CartVO cvo = new CartVO();

        //1. driver연결
        //2. 계정연결

        Connection conn = ConnectionSingletonHelper.getConnection("mariadb");
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT CARTID, USERID, BOOKID, PURCHASE FROM CART WHERE USERID = " +str;

        try{
            //3. query 준비
            stmt = conn.createStatement();
            //4. 실행 및 리턴
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                cvo.setcartId(rs.getInt("cartId"));
                cvo.setUserId(rs.getString("userId"));
                cvo.setBookId(rs.getInt("bookId"));
                cvo.setPurchase(rs.getInt("purchase"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            //5. db종료
            CloseHelper.close(rs);
            CloseHelper.close(stmt);
            CloseHelper.close(conn);

        }
        return cvo;
    }

    //도서코드에 대한 장바구니 출력
    public CartVO selectByBookId(int no){
        CartVO cvo = new CartVO();

        //1. driver연결
        //2. 계정연결

        Connection conn = ConnectionSingletonHelper.getConnection("mariadb");
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT CARTID, USERID, BOOKID, PURCHASE FROM CART WHERE BOOKID = " +no;

        try{
            //3. query 준비
            stmt = conn.createStatement();
            //4. 실행 및 리턴
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                cvo.setcartId(rs.getInt("cartId"));
                cvo.setUserId(rs.getString("userId"));
                cvo.setBookId(rs.getInt("bookId"));
                cvo.setPurchase(rs.getInt("purchase"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            //5. db종료
            CloseHelper.close(rs);
            CloseHelper.close(stmt);
            CloseHelper.close(conn);

        }
        return cvo;
    }

    //추가
    public int insertCart(CartVO cvo){
        //1. driver연결
        //2. 계정연결

        Connection conn = ConnectionSingletonHelper.getConnection("mariadb");
        PreparedStatement pstmt = null;
        int res = 0;

        String sql = "INSERT INTO CART VALUES(?,?,?)";  //USERID, BOOKID, PURCHASE

        try {
            //3. query 준비
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,cvo.getUserId());
            pstmt.setInt(2,cvo.getBookId());
            pstmt.setInt(3,cvo.getPurchase());

            //4. 실행 및 리턴
            res = pstmt.executeUpdate();
            if (res >0){
                conn.commit();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            CloseHelper.close(pstmt);
            CloseHelper.close(conn);
        }
        return res;
    }

    //수정
    public int updateCart(CartVO cvo){
        //1. driver연결
        //2. 계정연결
        Connection conn = ConnectionSingletonHelper.getConnection("mariadb");
        PreparedStatement pstmt = null;
        int res = 0;

        String sql = "UPDATE CART SET USERID = ?, BOOKID = ?, PURCHASE = ? WHERE CARTID = ?"; //USERID, BOOKID, PURCHASE

        try {
            //3. query 준비
            pstmt = conn.prepareStatement(sql);

            //4. 실행 및 리턴
            pstmt.setString(1,cvo.getUserId());
            pstmt.setInt(2,cvo.getBookId());
            pstmt.setInt(3,cvo.getPurchase());
            pstmt.setInt(4,cvo.getcartId());

            res = pstmt.executeUpdate();
            if(res > 0){
                conn.commit();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            //5.db종료
            CloseHelper.close(pstmt);
            CloseHelper.close(conn);
        }

        return res;
    }

    //삭제
    public int deleteCart(CartVO cvo){
        //1. driver연결
        //2. 계정연결
        Connection conn = ConnectionSingletonHelper.getConnection("mariadb");
        PreparedStatement pstmt = null;
        int res = 0;

        String sql = "DELETE FROM FAVORITE WHERE CARTID = ?";

        try {
            //3. query문 준비
            pstmt = conn.prepareStatement(sql);

            //4. 실행 및 리턴
            pstmt.setInt(1, cvo.getcartId());

            res = pstmt.executeUpdate();
            if (res > 0) {
                conn.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseHelper.close(pstmt);
            CloseHelper.close(conn);
        }
        return res;
    }
}
