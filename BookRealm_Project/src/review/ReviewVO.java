package review;

import java.sql.Date;

public class ReviewVO {
    private int reviewId;       // 리뷰 번호
    private int popular;        // 별점
    private String contents;    // 리뷰 작성 내용
    private Date reportDate;    // 리뷰 작성 일자(mariaDB DateTime -> java.sql.Date 로 매핑) / 안되면 수정할 예정
    private String userId;      // 유저아이디(작성자 아이디)
    private int bookId;         // 도서코드

    public int getReviewId() {
        return reviewId;
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

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
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
