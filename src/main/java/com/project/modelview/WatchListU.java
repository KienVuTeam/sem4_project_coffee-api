package com.project.modelview;

import java.math.BigDecimal;

public class WatchListU {
	private int id;
	private int idProduct;
	private String productName;
	private BigDecimal productPrice;
	private BigDecimal discountPrice;
	private String productImage;
	private int saleNumbers;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}
	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public int getSaleNumbers() {
		return saleNumbers;
	}
	public void setSaleNumbers(int saleNumbers) {
		this.saleNumbers = saleNumbers;
	}
	
	
}
