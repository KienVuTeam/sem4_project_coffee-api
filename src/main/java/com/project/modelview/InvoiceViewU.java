package com.project.modelview;

import java.math.BigDecimal;
import java.sql.Date;

public class InvoiceViewU {
	private int id;
	private int idAccount;
	private String address;
	private BigDecimal totalPrice;
	private String idPayment;
	private String idVoucherS;
	private String idVoucherA;
	private Date createDate;
	private int isStatus;
	private String codeInvoice;
	private int quantity; 
	//view
	private String userName;
	private String userPhone;
	private BigDecimal totalDiscountV;
	private BigDecimal profitsAdmin;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getCodeInvoice() {
		return codeInvoice;
	}
	public void setCodeInvoice(String codeInvoice) {
		this.codeInvoice = codeInvoice;
	}
	public BigDecimal getTotalDiscountV() {
		return totalDiscountV;
	}
	public void setTotalDiscountV(BigDecimal totalDiscountV) {
		this.totalDiscountV = totalDiscountV;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getProfitsAdmin() {
		return profitsAdmin;
	}
	public void setProfitsAdmin(BigDecimal profitsAdmin) {
		this.profitsAdmin = profitsAdmin;
	}
	
	
	
	
}
