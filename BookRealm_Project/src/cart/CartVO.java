package cart;

public class CartVO {
    private int cartId;  // 장바구니 코드 (PK)
    private int purchase;  //수량
    private int bookId; // 도서코드
    private String userId; //회원ID


    public int getcartId() {
        return cartId;
    }

    public void setcartId(int cartId) {
        this.cartId = cartId;
    }

    public int getPurchase() {
        return purchase;
    }

    public void setPurchase(int purchase) {
        this.purchase = purchase;
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
