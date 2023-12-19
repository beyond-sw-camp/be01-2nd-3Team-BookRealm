package order;

import ConnUtil.ConnectionSingletonHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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

}
