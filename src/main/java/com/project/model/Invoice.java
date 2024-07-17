package com.project.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Invoice {
	private int id;
	private int idAccount;
	private String address;
	private String notes;
	private BigDecimal totalPrice;
	private String idPayment;
	private String idVoucherS;
	private String idVoucherA;
	private Date createDate;
	private int isStatus;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getIdPayment() {
		return idPayment;
	}
	public void setIdPayment(String idPayment) {
		this.idPayment = idPayment;
	}
	public String getIdVoucherS() {
		return idVoucherS;
	}
	public void setIdVoucherS(String idVoucherS) {
		this.idVoucherS = idVoucherS;
	}
	public String getIdVoucherA() {
		return idVoucherA;
	}
	public void setIdVoucherA(String idVoucherA) {
		this.idVoucherA = idVoucherA;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getIsStatus() {
		return isStatus;
	}
	public void setIsStatus(int isStatus) {
		this.isStatus = isStatus;
	}
	public Invoice(int id, int idAccount, String address, String notes, BigDecimal totalPrice, String idPayment,
			String idVoucherS, String idVoucherA, Date createDate, int isStatus) {
		super();
		this.id = id;
		this.idAccount = idAccount;
		this.address = address;
		this.notes = notes;
		this.totalPrice = totalPrice;
		this.idPayment = idPayment;
		this.idVoucherS = idVoucherS;
		this.idVoucherA = idVoucherA;
		this.createDate = createDate;
		this.isStatus = isStatus;
	}
	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
