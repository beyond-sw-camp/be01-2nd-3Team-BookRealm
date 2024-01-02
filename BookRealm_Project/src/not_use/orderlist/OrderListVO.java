package not_use.orderlist;

public class        OrderListVO {

    private int buyId;      // 구매코드
    private int bookId;     // 도서코드
    private int purchase;   // 가격
    private String status;  // 주문상태

    public OrderListVO() {}

    public OrderListVO(int buyId, int bookId, int purchase, String status) {
        this.buyId = buyId;
        this.bookId = bookId;
        this.purchase = purchase;
        this.status = status;
    }

    public int getBuyId() {
        return buyId;
    }

    public void setBuyId(int buyId) {
        this.buyId = buyId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getPurchase() {
        return purchase;
    }

    public void setPurchase(int purchase) {
        this.purchase = purchase;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
