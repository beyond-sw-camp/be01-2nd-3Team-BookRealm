package order;

import ConnUtil.ConnectionSingletonHelper;

import java.sql.*;

import static ConnUtil.CloseHelper.closeAll;

public class OrderDAO {
    static Statement stmt = null;
    static ResultSet rs = null;
    static Connection conn = null;
    static PreparedStatement pstmt = null;

    public OrderDAO() {
        conn = ConnectionSingletonHelper.getConnection("mariadb");
    }

    //select는 조인이 필요할거같아서 구현안함

    //insert
    public int insert(OrderVO vo) throws SQLException {
        int result = 0;
        pstmt = conn.prepareStatement("insert into order (payment, destination, userId)"
                + "values(?,?,?)");

        pstmt.setString(1, vo.getPayment());
        pstmt.setString(2, vo.getDestination());
        pstmt.setInt(3, vo.getUserId());

        // 작업 객체를 활용하여 쿼리문 실행(전달)
        result = pstmt.executeUpdate();

        //반환
        closeAll(conn, pstmt, rs);

        // 최종 결과값 반환
        return result;
    }

    //delete
    public int delete(int id) throws SQLException {
        //반환할 결과값
        int result = 0;

        // 작업 객체 생성, 실행
        pstmt = conn.prepareStatement("DELETE from Order where OrderId = ?");
        pstmt.setInt(1, id);

        result = pstmt.executeUpdate();

        //반환
        closeAll(conn, pstmt, rs);

        return result;
    }

    //update (회원 정보 수정)
    public int update(String field, String ud, String id) throws SQLException {
        //반환할 결과값
        int result = 0;

        // 작업 객체 생성, 실행
        pstmt = conn.prepareStatement("UPDATE Order set ? = ? where OrderId = ?");
        pstmt.setString(1, field);
        pstmt.setString(2, ud);
        pstmt.setString(3, id);

        result = pstmt.executeUpdate();

        //반환
        closeAll(conn, pstmt, rs);

        return result;
    }
}
