package BookSearchProgram;

import java.sql.Date;

public class BSP_VO {
	
	private int bookId, price, stock, sales;
    private String category, writer, title, publisher;
    private Date publishDate;

    public BSP_VO() {
    	super();
    }
    
	public BSP_VO(int bookId, int price, int stock, int sales, String category, String writer, String title,
			String publisher, Date publishDate) {
		super();
		this.bookId = bookId;
		this.price = price;
		this.stock = stock;
		this.sales = sales;
		this.category = category;
		this.writer = writer;
		this.title = title;
		this.publisher = publisher;
		this.publishDate = publishDate;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Override
	public String toString() {
		return " [bookId=" + bookId + " category=" + category + ", writer=" + writer + ", title=" + title + "]";
	}

}
