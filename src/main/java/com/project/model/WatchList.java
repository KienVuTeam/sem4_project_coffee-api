package com.project.model;

import java.sql.Date;

public class WatchList {
	private int id;
	private int idProduct;
	private int idAccount;
	private Date createDate;
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
	public int getIdAccount() {
		return idAccount;
	}
	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public WatchList(int id, int idProduct, int idAccount, Date createDate) {
		super();
		this.id = id;
		this.idProduct = idProduct;
		this.idAccount = idAccount;
		this.createDate = createDate;
	}
	public WatchList() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
