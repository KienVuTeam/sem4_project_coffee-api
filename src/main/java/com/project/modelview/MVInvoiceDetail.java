package com.project.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MVInvoiceDetail {


//    private String nameCus;
//    private String createDate;
//    private int isStatus;
    private String nameProduct;
    private float unitPrice;
//    private String voucherS;
//    private float totalPrice;
//    private String voucherA;
    private float amount;
    private float price;
    
    @JsonCreator
    public MVInvoiceDetail(@JsonProperty("amount") float amount, @JsonProperty("nameProduct") String nameProduct,
    		@JsonProperty("unitPrice") float unitPrice, @JsonProperty("price") float price) {
        this.price = price;
        this.nameProduct = nameProduct;
        this.unitPrice =unitPrice;
        this.amount =amount;
    }
    
	public MVInvoiceDetail(String nameProduct, float unitPrice, float amount, float price) {
		super();
		
		this.nameProduct = nameProduct;
		this.unitPrice = unitPrice;
		this.amount = amount;
		this.price = price;
	}

	public String getNameProduct() {
		return nameProduct;
	}
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}
	public float getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
	
	
	
	
}
