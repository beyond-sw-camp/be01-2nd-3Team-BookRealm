package user;

import ConnUtil.ConnectionSingletonHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import static ConnUtil.CloseHelper.close;

public class UserDao {
    static Statement stmt = null;
    static ResultSet rs = null;
    static Connection conn = null;
    static PreparedStatement pstmt = null;

    public UserDao() {
        conn = ConnectionSingletonHelper.getConnection("mariadb");
    }


    //selectAll
    public ArrayList<UserVo> selectAll() throws SQLException {
        ArrayList<UserVo> result = new ArrayList<>();

        // 작업 객체 생성
        Statement stmt = conn.createStatement();

        // 쿼리문 준비 → select
        String sql = "SELECT * FROM USER";

        // 생성된 작업 객체를 활용하여 쿼리문 실행 → select → executeQuery() → ResultSet 반환 → 일반적으로 반복 처리
        ResultSet rs = stmt.executeQuery(sql);

        // ResultSet 처리 → 일반적 반복문 활용
        while (rs.next()) {
            UserVo vo = new UserVo();

            vo.setUserId(rs.getInt("userId"));
            vo.setUsername(rs.getString("username"));
            vo.setPasswd(rs.getString("passwd"));
            vo.setAddress(rs.getString("address"));
            vo.setPhone(rs.getString("phone"));
            vo.setSuType(rs.getString("suType"));
            vo.setAdminyn(rs.getInt("adminyn"));

            result.add(vo);
        }

        //반환
        close(rs);
        close(stmt);

        return result;
    }

    //select by id
    public UserVo selectById(int id) throws SQLException {
        UserVo result = new UserVo();

        // 작업 객체 생성
        PreparedStatement pstmt = conn.prepareStatement("select * from User where userId = ?");
        pstmt.setInt(1, id);
        rs = pstmt.executeQuery();  // 반환값 있는 경우

        ResultSetMetaData rsmd = rs.getMetaData();
        int count = rsmd.getColumnCount();


        return result;
    }

}
