package com.project.model;

import java.math.BigDecimal;
import java.sql.Date;

public class InvoiceSupplier {
	private int id;
	private int idSupplier;
	private BigDecimal totalPrice;
	private Date createDate ;
	private int year;
	private int idInvoice;
	private int status;
	private int sttInvoice;
	private BigDecimal price;
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	

	public InvoiceSupplier(int id, int idSupplier, BigDecimal totalPrice, Date createDate, int idInvoice, int status,
			int sttInvoice, BigDecimal price) {
		super();
		this.id = id;
		this.idSupplier = idSupplier;
		this.totalPrice = totalPrice;
		this.createDate = createDate;
		this.idInvoice = idInvoice;
		this.status = status;
		this.sttInvoice = sttInvoice;
		this.price = price;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdSupplier() {
		return idSupplier;
	}

	public void setIdSupplier(int idSupplier) {
		this.idSupplier = idSupplier;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal price) {
		this.totalPrice = price;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
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

	public int getSttInvoice() {
		return sttInvoice;
	}

	public void setSttInvoice(int sttInvoice) {
		this.sttInvoice = sttInvoice;
	}

	public InvoiceSupplier() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
