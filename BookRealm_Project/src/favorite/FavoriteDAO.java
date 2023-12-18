package favorite;

import ConnUtil.CloseHelper;
import ConnUtil.ConnectionSingletonHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FavoriteDAO {
    //전체 출력

    public List<FavoriteVO> selectAll() {
        List<FavoriteVO> list = new ArrayList<FavoriteVO>();

        //1. driver연결
        //2. 계정연결
        Connection conn = ConnectionSingletonHelper.getConnection("mariadb");
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT FAVORID, USERID, BOOKID FROM FAVORITE";

        try {
            //3. query 준비
            stmt = conn.createStatement();
            //실행 및 리턴
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                FavoriteVO fvo = new FavoriteVO();
                fvo.setFavorId(rs.getInt("FavorId"));
                fvo.setUserId(rs.getString("userId"));
                fvo.setBookId(rs.getInt("bookId"));


                list.add(fvo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //5.db 종료
            CloseHelper.close(rs);
            CloseHelper.close(stmt);
            CloseHelper.close(conn);

        }
        return list;
    }

    //도서코드에 대한 전체 즐겨찾기 출력
    public FavoriteVO selectByBookId(int no) {
        FavoriteVO fvo = new FavoriteVO();

        //1. driver연결
        //2. 계정연결
        Connection conn = ConnectionSingletonHelper.getConnection("mariadb");
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT FAVORID, USERID, BOOKID FROM FAVORITE WHERE BOOKID = " + no;

        try {
            //3. query 준비
            stmt = conn.createStatement();
            //실행 및 리턴
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                fvo.setFavorId(rs.getInt("FavorId"));
                fvo.setUserId(rs.getString("userId"));
                fvo.setBookId(rs.getInt("bookId"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //5.db 종료
            CloseHelper.close(rs);
            CloseHelper.close(stmt);
            CloseHelper.close(conn);

        }

        return fvo;

    }

    //회원별 전체 즐겨찾기 출력
    public FavoriteVO selectByUserId(String str) {
        FavoriteVO fvo = new FavoriteVO();

        //1. driver연결
        //2. 계정연결
        Connection conn = ConnectionSingletonHelper.getConnection("mariadb");
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT FAVORID, USERID, BOOKID FROM FAVORITE WHERE USERID = " + str;

        try {
            //3. query 준비
            stmt = conn.createStatement();
            //실행 및 리턴
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                fvo.setFavorId(rs.getInt("FavorId"));
                fvo.setUserId(rs.getString("userId"));
                fvo.setBookId(rs.getInt("bookId"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //5.db 종료
            CloseHelper.close(rs);
            CloseHelper.close(stmt);
            CloseHelper.close(conn);

        }

        return fvo;

    }

    //추가
    public int insertFavorite(FavoriteVO fvo) {

        //1. driver연결
        //2. 계정연결
        Connection conn = ConnectionSingletonHelper.getConnection("mariadb");
        PreparedStatement pstmt = null;
        int res = 0;

        String sql = "INSERT INTO FAVORITE VALUES(?,?)";

        try {
            //3. query 준비
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, fvo.getUserId());
            pstmt.setInt(2, fvo.getBookId());

            //4. 실행 및 리턴
            res = pstmt.executeUpdate();
            if (res > 0) {
                conn.commit();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //5.db종료
            CloseHelper.close(pstmt);
            CloseHelper.close(conn);
        }
        return res;  // 이부분 이해 안됨.

    }

    //수정
    public int updateFavorite(FavoriteVO fvo) {
        //1. driver연결
        //2. 계정연결
        Connection conn = ConnectionSingletonHelper.getConnection("mariadb");
        PreparedStatement pstmt = null;
        int res = 0;

        String sql = "UPDATE FAVORITE SET USERID = ?, BOOKID = ? WHERE FAVORID = ?";

        try {
            //3. query 준비
            pstmt = conn.prepareStatement(sql);

            //4. 실행 및 리턴
            pstmt.setString(1, fvo.getUserId());
            pstmt.setInt(2, fvo.getBookId());
            pstmt.setInt(3, fvo.getFavorId());

            res = pstmt.executeUpdate();
            if (res > 0) {
                conn.commit();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //5.db종료
            CloseHelper.close(pstmt);
            CloseHelper.close(conn);
        }
        return res;  // 이부분 이해 안됨.

    }

    //삭제
    public int deleteFavorite(FavoriteVO fvo) {
        //1. driver연결
        //2. 계정연결
        Connection conn = ConnectionSingletonHelper.getConnection("mariadb");
        PreparedStatement pstmt = null;
        int res = 0;

        String sql = "DELETE FROM FAVORITE WHERE FAVORID = ?";

        try {
            //3. query문 준비
            pstmt = conn.prepareStatement(sql);

            //4. 실행 및 리턴
            pstmt.setInt(1, fvo.getFavorId());

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
