package review;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewController {

    private static ReviewDAO reviewDAO;
    private ResultSet rs;

    public ReviewController() {
        reviewDAO = new ReviewDAO();
    }

    // 리뷰 전체를 보여줌
    public void showAll() {
        rs = reviewDAO.selectAll();
        // 결과 화면 추가해야함.
    }

    public void showByBookId(int bookId) {
        rs = reviewDAO.selectByBookId(bookId);
        // 결과 화면 추가해야함.
    }

    public void showByUserId(String userId){
        rs = reviewDAO.selectByUserId(userId);
        // 결과 화면 추가해야함.
    }

    public void writeReview() throws SQLException {
        ReviewVO vo = new ReviewVO();        // view에서 입력받은 작성한 리뷰 객체 받아옴
        reviewDAO.writeReview(vo);
        // 작성 완료 화면 + 기존 화면 리다이렉션
    }

    public void editReview() throws SQLException {
        int reviewId = 0;   // 수정한 리뷰 아이디를 입력받는 화면 추가해야함.
        rs = reviewDAO.selectByReviewId(reviewId);
        ReviewVO vo = new ReviewVO();   // rs의 값을 수정하고 신규 VO를 받아오는 화면 추가해야함.
        reviewDAO.editReview(vo);
        // 리뷰 수정 완료 화면 추가해야함 + 리다이렉션
    }

    public void deleteReview() throws SQLException {
        int reviewId = 0;   //  삭제할 아이디를 받아오는 화면 추가해야함.
        reviewDAO.deleteReview(reviewId);
        // 삭제 완료 화면 추가해야함 + 리다이렉션
    }
}
