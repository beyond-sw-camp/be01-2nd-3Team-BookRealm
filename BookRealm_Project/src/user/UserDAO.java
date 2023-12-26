package user;

import ConnUtil.CloseHelper;
import ConnUtil.ConnectionSingletonHelper;
import java.sql.*;
import java.util.ArrayList;
import static ConnUtil.CloseHelper.closeAll;

public class UserDAO {
    static Statement stmt = null;
    static ResultSet rs = null;
    static Connection conn = null;
    static PreparedStatement pstmt = null;

    public UserDAO() {
        conn = ConnectionSingletonHelper.getConnection("mariadb");
    }


    //selectAll (전체 회원 조회)
    public ArrayList<UserVO> selectAll() throws SQLException {
        ArrayList<UserVO> result = new ArrayList<>();
        UserVO vo = null;
        // 작업 객체 생성
        stmt = conn.createStatement();

        // 쿼리문 준비 → select
        String sql = "SELECT * FROM USER";

        // 생성된 작업 객체를 활용하여 쿼리문 실행 → select → executeQuery() → ResultSet 반환 → 일반적으로 반복 처리
        rs = stmt.executeQuery(sql);

        // ResultSet 처리 → 일반적 반복문 활용
        while (rs.next()) {
            vo = new UserVO();

            vo.setUserId(rs.getString("userId"));
            vo.setUsername(rs.getString("username"));
            vo.setPasswd(rs.getString("passwd"));
            vo.setAddress(rs.getString("address"));
            vo.setPhone(rs.getString("phone"));
            vo.setSuType(rs.getString("suType"));
            vo.setAdminyn(rs.getInt("adminyn"));

            result.add(vo);
        }

        //반환
        closeAll(conn, stmt, rs);

        return result;
    }

    //select by id (회원 정보 조회)
    public UserVO selectById(String id) throws SQLException {
        UserVO vo = new UserVO();

        // 작업 객체 생성
        pstmt = conn.prepareStatement("select * from User where userId = ?");
        pstmt.setString(1, id);
        rs = pstmt.executeQuery();  // 반환값 있는 경우

        ResultSetMetaData rsmd = rs.getMetaData();
        int count = rsmd.getColumnCount();

        if (rs.next()) {
            vo.setUserId(rs.getString("userId"));
            vo.setUsername(rs.getString("username"));
            vo.setPasswd(rs.getString("passwd"));
            vo.setAddress(rs.getString("address"));
            vo.setPhone(rs.getString("phone"));
            vo.setSuType(rs.getString("suType"));
            vo.setAdminyn(rs.getInt("adminyn"));
        }

        //반환
        closeAll(conn,pstmt,rs);
        return vo;
    }

    //select passwd (로그인 할 때 비밀번호 조회)
    public int login(String uId, String uPasswd) throws SQLException {
        int result = -1;

        pstmt = conn.prepareStatement("select passwd from User where userId = ?");
        pstmt.setString(1,uId);

        rs = pstmt.executeQuery();  // 반환값 있는 경우

        if(rs.next()) {
            if(rs.getString(1).equals(uPasswd)){
                result = 1; //로그인 성공
            }
            else {
                result = 0; //비밀번호 불일치
            }
        }

        closeAll(conn,pstmt,rs);
        return result; // 아이디 불일치
    }

    //insert (회원가입)
    public int join(UserVO vo) throws SQLException
    {
        // 반환할 결과값을 담아낼 변수 (적용된 행의 갯수)
        int result = 0;
        // 작업 객체 생성
        pstmt = conn.prepareStatement("INSERT INTO User(userid, username, passwd, addresss, phone, sutype, adminyn)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?)");

        pstmt.setString(1,vo.getUserId());
        pstmt.setString(2, vo.getUsername());
        pstmt.setString(3, vo.getPasswd());
        pstmt.setString(4, vo.getAddress());
        pstmt.setString(5, vo.getPhone());
        pstmt.setString(6, vo.getSuType());
        pstmt.setInt(7, vo.getAdminyn());

        // 작업 객체를 활용하여 쿼리문 실행(전달)
        result = pstmt.executeUpdate();

        //반환
        closeAll(conn, pstmt, rs);

        // 최종 결과값 반환
        return result;

    }//end add()

    //delete (회원 탈퇴)
    public int delete(String id) throws SQLException {
        //반환할 결과값
        int result = 0;

        // 작업 객체 생성, 실행
        pstmt = conn.prepareStatement("DELETE from User where userID = ?");
        pstmt.setString(1, id);

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
        pstmt = conn.prepareStatement("UPDATE User set ? = ? where UserId = ?");
        pstmt.setString(1, field);
        pstmt.setString(2, ud);
        pstmt.setString(3, id);

        result = pstmt.executeUpdate();

        //반환
        closeAll(conn, pstmt, rs);

        return result;
    }

    public int updateAll(UserVO vo) throws SQLException {
        int result = 0;

        // 쿼리문 준비 - update
        String sql = "UPDATE USER SET VALUES( ?, ?, ?, ?, ?, ?, ?) WHERE USERID = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getUserId());
            pstmt.setString(2, vo.getUsername());
            pstmt.setString(3, vo.getPasswd());
            pstmt.setString(4, vo.getAddress());
            pstmt.setString(5, vo.getPhone());
            pstmt.setString(6, vo.getSuType());
            pstmt.setInt(7, vo.getAdminyn());
            pstmt.setString(8, vo.getUserId());

            result = pstmt.executeUpdate();
            if (result > 0) {
                conn.commit();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 닫기 (자원 반환)
            CloseHelper.close(pstmt);
            CloseHelper.close(conn);
        }
        return result;
    }
}
