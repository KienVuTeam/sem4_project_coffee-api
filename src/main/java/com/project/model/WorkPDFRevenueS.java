package com.project.model;

import java.math.BigDecimal;

public class WorkPDFRevenueS {
	private String productName;
	private BigDecimal productPrice;
	private int quanity;
	private BigDecimal totalPrice;
	private BigDecimal profitS;
	private BigDecimal profitA;
	private int isStatus;

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
	public int getQuanity() {
		return quanity;
	}
	public void setQuanity(int quanity) {
		this.quanity = quanity;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public BigDecimal getProfitS() {
		return profitS;
	}
	public void setProfitS(BigDecimal profitS) {
		this.profitS = profitS;
	}	
	public int getIsStatus() {
		return isStatus;
	}
	public void setIsStatus(int isStatus) {
		this.isStatus = isStatus;
	}
	public BigDecimal getProfitA() {
		return profitA;
	}
	public void setProfitA(BigDecimal profitA) {
		this.profitA = profitA;
	}
}
