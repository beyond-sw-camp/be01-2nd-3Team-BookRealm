package review;

import ConnUtil.ConnectionSingletonHelper;

import java.sql.*;

public class ReviewDAO {

    static Statement stmt = null;
    static ResultSet rs = null;
    static Connection conn = null;
    static PreparedStatement pstmt = null;

    public ReviewDAO() {
        conn = ConnectionSingletonHelper.getConnection("mariadb");
    }

    // 전체 리뷰 조회
    public ResultSet selectAll() {
        return rs;
    }

    // 도서코드에 대한 전체 리뷰 조회
    public ResultSet selectByBookId(int bookId){
        return rs;
    }

    // 유저가 작성한 리뷰들 전체 조회
    public ResultSet selectByUserId(String userId){
        return rs;
    }

    public ResultSet selectByReviewId(int reviewId){
        return rs;
    }

    // 신규 리뷰 작성
    public void writeReview(ReviewVO vo) throws SQLException {
        pstmt = conn.prepareStatement("INSERT INTO REVIEW VALUES( ?, ?, ?, ?, ?, ?)");
    }

    // 작성한 리뷰 수정
    public void editReview(ReviewVO vo) throws SQLException {
        pstmt = conn.prepareStatement("UPDATE REVIEW SET VALUES( ?, ?, ?, ?, ?, ?) WHERE review_id = ?");
    }

    // 작성한 리뷰 삭제
    public void deleteReview(int reviewId) throws SQLException {
        pstmt = conn.prepareStatement("DELETE FROM REVIEW WHERE reviewId = ?");
    }
}
