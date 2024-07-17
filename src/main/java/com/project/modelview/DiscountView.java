package com.project.modelview;

import java.math.BigDecimal;
import java.sql.Date;

public class DiscountView {
	private int id;
	private BigDecimal discount;
	private Date dateBegin;
	private Date dateEnd;
	private int idProduct;
	private BigDecimal priceSale;
	private int isStatus;
	
	
	//new 22-11
	private String image;
	
	
	//product
	private String title;
	private BigDecimal price;
	//Expiered
	private int timeExpiered;
	
	
	//new
	private int indC;
	
	public DiscountView(int id, BigDecimal discount, Date dateBegin, Date dateEnd, int idProduct, BigDecimal priceSale,
			int isStatus, String image, String title, BigDecimal price, int timeExpiered, int indC) {
		super();
		this.id = id;
		this.discount = discount;
		this.dateBegin = dateBegin;
		this.dateEnd = dateEnd;
		this.idProduct = idProduct;
		this.priceSale = priceSale;
		this.isStatus = isStatus;
		this.image = image;
		this.title = title;
		this.price = price;
		this.timeExpiered = timeExpiered;
		this.indC = indC;
	}
	public int getIndC() {
		return indC;
	}
	public void setIndC(int indC) {
		this.indC = indC;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public Date getDateBegin() {
		return dateBegin;
	}
	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	public BigDecimal getPriceSale() {
		return priceSale;
	}
	public void setPriceSale(BigDecimal priceSale) {
		this.priceSale = priceSale;
	}
	public int getIsStatus() {
		return isStatus;
	}
	public void setIsStatus(int isStatus) {
		this.isStatus = isStatus;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getTimeExpiered() {
		return timeExpiered;
	}
	public void setTimeExpiered(int timeExpiered) {
		this.timeExpiered = timeExpiered;
	}
	public DiscountView() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
