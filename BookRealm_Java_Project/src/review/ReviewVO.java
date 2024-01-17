package review;

import java.sql.Date;
import java.time.LocalDateTime;

public class ReviewVO {
    private int reviewId;       // 리뷰 번호
    private int popular;        // 별점
    private String contents;    // 리뷰 작성 내용
    private LocalDateTime  reportDate;    // 리뷰 작성 일자(mariaDB DateTime -> java.sql.Date 로 매핑) / 안되면 수정할 예정
      // LocalDateTime으로 수정 요즘은 Date안쓴다함.(우용)
    private String userId;      // 유저아이디(작성자 아이디)
    private int bookId;         // 도서코드

    public ReviewVO() {

    }

    public ReviewVO(int popular, String contents, LocalDateTime reportDate, String userId, int bookId) {
        this.popular = popular;
        this.contents = contents;
        this.reportDate = reportDate;
        this.userId = userId;
        this.bookId = bookId;
    }

    public int getReviewId() {
        return reviewId;
    }

    public ReviewVO(int popular, String contents) {
        this.popular = popular;
        this.contents = contents;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getPopular() {
        return popular;
    }

    public void setPopular(int popular) {
        this.popular = popular;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public LocalDateTime getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDateTime reportDate) {
        this.reportDate = reportDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
