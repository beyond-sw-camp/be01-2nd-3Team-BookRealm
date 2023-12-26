package ReviewController;

import book.BookVO;
import review.ReviewVO;

public class JoinVo {
    private BookVO bookVO;
    private ReviewVO reviewVO;

    public JoinVo(BookVO bookVO, ReviewVO reviewVO) {
        this.bookVO = bookVO;
        this.reviewVO = reviewVO;
    }

    public BookVO getBookVO() {
        return bookVO;
    }

    public void setBookVO(BookVO bookVO) {
        this.bookVO = bookVO;
    }

    public ReviewVO getReviewVO() {
        return reviewVO;
    }

    public void setReviewVO(ReviewVO reviewVO) {
        this.reviewVO = reviewVO;
    }
}
