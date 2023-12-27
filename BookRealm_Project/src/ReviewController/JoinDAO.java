package ReviewController;

import ConnUtil.CloseHelper;
import ConnUtil.ConnectionSingletonHelper;
import book.BookVO;
import review.ReviewVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class JoinDAO {
    static PreparedStatement pstmt = null;
    static ResultSet rs = null;
    static Connection conn = null;

    public JoinVo JoinBookAndReview(int bookId) {
        JoinVo joinVo = null;
        try {
            String sql = "SELECT b.category, b.title, b.writer, b.price, b.stock, b.publisher, " +
                    "r.contents, r.popular, r.reportDate " +
                    "FROM book b " +
                    "JOIN review r ON b.bookId = r.bookId " +
                    "WHERE b.bookId = ?";
            conn = ConnectionSingletonHelper.getConnection("mariadb");
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bookId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // 도서 정보 설정
                BookVO bookVO = new BookVO();
                bookVO.setCategory(rs.getString("category"));
                bookVO.setTitle(rs.getString("title"));
                bookVO.setWriter(rs.getString("writer"));
                bookVO.setPrice(rs.getInt("price"));
                bookVO.setStock(rs.getInt("stock"));
                bookVO.setPublisher(rs.getString("publisher"));

                // 리뷰 정보 설정
                ReviewVO reviewVO = new ReviewVO();
                reviewVO.setContents(rs.getString("contents"));
                reviewVO.setPopular(rs.getInt("popular"));
                reviewVO.setReportDate(rs.getTimestamp("reportDate").toLocalDateTime());

                // 도서와 리뷰 정보 결합
                joinVo = new JoinVo(bookVO, reviewVO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseHelper.close(rs);
            CloseHelper.close(pstmt);
            CloseHelper.close(conn);

        }
        return joinVo;

    }
}
