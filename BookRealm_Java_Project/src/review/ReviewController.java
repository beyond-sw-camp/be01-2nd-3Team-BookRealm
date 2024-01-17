package review;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReviewController {

    private static ReviewDAO reviewDAO;

    public ReviewController() {
        reviewDAO = new ReviewDAO();
    }

    // 리뷰 전체를 보여줌
    public void showAll() throws SQLException {


    }

    public void showByBookId(int bookId) throws SQLException {

    }

    public void showByUserId(String userId) throws SQLException {

    }

    public void writeReview() throws SQLException {

    }

    public void editReview() throws SQLException {

    }

    public void deleteReview() throws SQLException {

    }
}
