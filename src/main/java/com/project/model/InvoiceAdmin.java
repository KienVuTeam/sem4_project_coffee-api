package com.project.model;

import java.math.BigDecimal;
import java.sql.Date;

public class InvoiceAdmin {
	private int id;
	private BigDecimal price;
	private Date createDate;
	private int idInvoice;
	private int status;
	private int isStatus;
	private BigDecimal feeService;
	
	public InvoiceAdmin(int id, BigDecimal price, Date createDate, int idInvoice, int status, int isStatus,
			BigDecimal feeService) {
		super();
		this.id = id;
		this.price = price;
		this.createDate = createDate;
		this.idInvoice = idInvoice;
		this.status = status;
		this.isStatus = isStatus;
		this.feeService = feeService;
	}
	public BigDecimal getFeeService() {
		return feeService;
	}
	public void setFeeService(BigDecimal feeService) {
		this.feeService = feeService;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getIdInvoice() {
		return idInvoice;
	}
	public void setIdInvoice(int idInvoice) {
		this.idInvoice = idInvoice;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getIsStatus() {
		return isStatus;
	}
	public void setIsStatus(int isStatus) {
		this.isStatus = isStatus;
	}
	public InvoiceAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}

