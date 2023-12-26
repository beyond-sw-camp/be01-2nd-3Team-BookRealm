package review;

import ConnUtil.ConnectionSingletonHelper;
import book.BookVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {

    static ResultSet rs = null;
    static Connection conn = null;
    static PreparedStatement pstmt = null;

    public ReviewDAO() {
        conn = ConnectionSingletonHelper.getConnection("mariadb");
    }

    // 전체 리뷰 조회
    public List<ReviewVO> selectAll() throws SQLException {
        pstmt = conn.prepareStatement("SELECT * FROM REVIEW");
        rs = pstmt.executeQuery();
        List<ReviewVO> list = new ArrayList<>();
        while (rs.next()) {

            ReviewVO vo = new ReviewVO();

            vo.setBookId(rs.getInt("bookId"));
            vo.setReviewId(rs.getInt("reviewId"));
            vo.setPopular(rs.getInt("popular"));
            vo.setContents(rs.getString("contents"));
            vo.setReportDate(rs.getDate("reportDate"));
            vo.setUserId(rs.getString("userId"));

            list.add(vo);

        }
        rs.close();
        return list;
    }

    // 도서코드에 대한 전체 리뷰 조회
    public ReviewVO selectByBookId(int bookId) throws SQLException {
        pstmt = conn.prepareStatement("SELECT * FROM REVIEW WHERE BOOKID = ?");
        pstmt.setInt(1,bookId);
        rs = pstmt.executeQuery();
        ReviewVO vo = new ReviewVO();
        while (rs.next()) {
            vo.setBookId(rs.getInt("bookId"));
            vo.setReviewId(rs.getInt("reviewId"));
            vo.setPopular(rs.getInt("popular"));
            vo.setContents(rs.getString("contents"));
            vo.setReportDate(rs.getDate("reportDate"));
            vo.setUserId(rs.getString("userId"));
        }

        rs.close();
        return vo;
    }

    // 유저가 작성한 리뷰들 전체 조회
    public List<ReviewVO> selectByUserId(String userId) throws SQLException {
        pstmt = conn.prepareStatement("SELECT * FROM REVIEW WHERE USERID = ?");
        pstmt.setString(1,userId);
        rs = pstmt.executeQuery();

        List<ReviewVO> list = new ArrayList<>();
        while (rs.next()) {

            ReviewVO vo = new ReviewVO();

            vo.setBookId(rs.getInt("bookId"));
            vo.setReviewId(rs.getInt("reviewId"));
            vo.setPopular(rs.getInt("popular"));
            vo.setContents(rs.getString("contents"));
            vo.setReportDate(rs.getDate("reportDate"));
            vo.setUserId(rs.getString("userId"));

            list.add(vo);

        }
        rs.close();
        return list;
    }

    public ReviewVO selectByReviewId(int reviewId) throws SQLException {
        pstmt = conn.prepareStatement("SELECT * FROM REVIEW WHERE REVIEWID = ?");
        pstmt.setInt(1,reviewId);
        rs = pstmt.executeQuery();
        ReviewVO vo = new ReviewVO();

        while (rs.next()) {
            vo.setBookId(rs.getInt("bookId"));
            vo.setReviewId(rs.getInt("reviewId"));
            vo.setPopular(rs.getInt("popular"));
            vo.setContents(rs.getString("contents"));
            vo.setReportDate(rs.getDate("reportDate"));
            vo.setUserId(rs.getString("userId"));
        }

        rs.close();
        return vo;
    }

    // 신규 리뷰 작성
    public void writeReview(ReviewVO vo) throws SQLException {
        pstmt = conn.prepareStatement("INSERT INTO REVIEW VALUES( ?, ?, ?, ?, ?, ?)");
        pstmt.setInt(1,vo.getReviewId());
        pstmt.setInt(2,vo.getPopular());
        pstmt.setString(3,vo.getContents());
        pstmt.setDate(4,vo.getReportDate());
        pstmt.setString(5,vo.getUserId());
        pstmt.setInt(6,vo.getBookId());
        int r = pstmt.executeUpdate();
    }

    // 작성한 리뷰 수정
    public void editReview(ReviewVO vo) throws SQLException {
        pstmt = conn.prepareStatement("UPDATE REVIEW SET VALUES( ?, ?, ?, ?, ?, ?) WHERE review_id = ?");
        pstmt.setInt(1,vo.getReviewId());
        pstmt.setInt(2,vo.getPopular());
        pstmt.setString(3,vo.getContents());
        pstmt.setDate(4,vo.getReportDate());
        pstmt.setString(5,vo.getUserId());
        pstmt.setInt(6,vo.getBookId());
        pstmt.setInt(7,vo.getReviewId());
        int r = pstmt.executeUpdate();
    }

    // 작성한 리뷰 삭제
    public void deleteReview(int reviewId) throws SQLException {
        pstmt = conn.prepareStatement("DELETE FROM REVIEW WHERE reviewId = ?");
        pstmt.setInt(1,reviewId);
        int r = pstmt.executeUpdate();
    }
}
