package com.project.model;

import java.math.BigDecimal;

public class Cart {
	private int id;
	private int idAccount;
	private int idProduct;
	private int Amount;
	private BigDecimal Price;
	private boolean Status;
	public Cart(int id, int idAccount, int idProduct, int amount, BigDecimal price, boolean status) {
		super();
		this.id = id;
		this.idAccount = idAccount;
		this.idProduct = idProduct;
		Amount = amount;
		Price = price;
		Status = status;
	}
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdAccount() {
		return idAccount;
	}
	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}
	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	public int getAmount() {
		return Amount;
	}
	public void setAmount(int amount) {
		Amount = amount;
	}
	public BigDecimal getPrice() {
		return Price;
	}
	public void setPrice(BigDecimal price) {
		Price = price;
	}
	public boolean isStatus() {
		return Status;
	}
	public void setStatus(boolean status) {
		Status = status;
	}
	
	
}
