package orderlist;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderListController {

    private static OrderListDAO orderListDAO;
    private ResultSet rs;

    public void showOrderList() throws SQLException {
        int buyId = 0;
        // 조회하는 주문 내역의 주문 코드 받는 화면
        rs = orderListDAO.selectAllList(buyId);
        // 주문 번호에 대한 전체 주문 내역을 표시하는 화면
    }

    public void addOrderList() throws SQLException {
        // 주문 내역에 추가하는 화면
        OrderListVO vo = new OrderListVO();
        orderListDAO.insertList(vo);
    }

    public void editOrderList() throws SQLException {
        int buyId = 0;
        // 수정할 주문 내역의 구매코드를 받는 화면
        // 주문 내역 수정 화면
        OrderListVO vo = new OrderListVO();
        orderListDAO.updateList(vo);
        // 수정 결과 화면 리다이렉션
    }

    public void deleteOrderList() throws SQLException {
        int buyId = 0;
        // 삭제할 주문 내역의 주문 코드를 받는 화면
        orderListDAO.deleteList(buyId);
        // 삭제 결과 화면 리다이렉션
    }

}
