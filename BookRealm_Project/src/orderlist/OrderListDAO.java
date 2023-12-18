package orderlist;

import ConnUtil.ConnectionSingletonHelper;

import java.sql.*;

public class OrderListDAO {

    static Statement stmt = null;
    static ResultSet rs = null;
    static Connection conn = null;
    static PreparedStatement pstmt = null;

    public OrderListDAO() {
        conn = ConnectionSingletonHelper.getConnection("mariadb");
    }

    // SELECT O.BUYID, L.BOOKID, L.PURCHASE, L.`STATUS`, O.PAYMENT, O.DESTINATION
    // FROM `order` O JOIN `orderlist` L ON O.BUYID = L.BUYID WHERE ID = ?;
    // 구매 코드에 따른 주문 내역 전체 리스트 조회
    public ResultSet selectAllList(int buyId) throws SQLException {
        pstmt = conn.prepareStatement("SELECT O.BUYID, L.BOOKID, L.PURCHASE, L.`STATUS`, O.PAYMENT, O.DESTINATION" +
                                        "FROM `order` O JOIN `orderlist` L ON O.BUYID = L.BUYID WHERE ID = ?");
        return rs;
    }

    // 주문 내역 추가
    public void insertList(OrderListVO vo) throws SQLException {
        pstmt = conn.prepareStatement("INSERT INTO `ORDERLIST` VALUES( ?, ?, ?, ?)");
    }

    // 주문 내역 수정
    public void updateList(OrderListVO vo) throws SQLException {
        pstmt = conn.prepareStatement("UPDATE `ORDERLIST` SET VALUES( ?, ?, ?, ?) WHERE BUYID = ?");
    }

    // 주문 내역 삭제
    public void deleteList(int buyId) throws SQLException {
        pstmt = conn.prepareStatement("DELETE FROM `ORDERLIST` WHERE BUYID = ?");
    }

}
