package not_use.favorite;

public class FavoriteVO {
    private int favorId;  //즐겨찾기 코드 (PK)
    private int bookId;  // 도서코드
    private String userId;  // 회원ID

    public int getFavorId() {
        return favorId;
    }

    public void setFavorId(int favorId) {
        this.favorId = favorId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
