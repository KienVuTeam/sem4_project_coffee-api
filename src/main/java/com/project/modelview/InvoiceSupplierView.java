package com.project.modelview;

import java.math.BigDecimal;
import java.sql.Date;

public class InvoiceSupplierView {
	private int id;
	private BigDecimal total;
	private Date date;
	private int month;
	
	private int idInvoice;
	private Date dateInvoice;
	private int discount;
	private BigDecimal condition;
	private BigDecimal totalInvoice;
	private BigDecimal iLAPay;
	private BigDecimal amountReceived;
	private BigDecimal totalA;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getIdInvoice() {
		return idInvoice;
	}
	public void setIdInvoice(int idInvoice) {
		this.idInvoice = idInvoice;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public BigDecimal getCondition() {
		return condition;
	}
	public void setCondition(BigDecimal condition) {
		this.condition = condition;
	}
	public BigDecimal getTotalInvoice() {
		return totalInvoice;
	}
	public void setTotalInvoice(BigDecimal totalInvoice) {
		this.totalInvoice = totalInvoice;
	}
	public BigDecimal getiLAPay() {
		return iLAPay;
	}
	public void setiLAPay(BigDecimal iLAPay) {
		this.iLAPay = iLAPay;
	}
	public BigDecimal getAmountReceived() {
		return amountReceived;
	}
	public void setAmountReceived(BigDecimal amountReceived) {
		this.amountReceived = amountReceived;
	}
	public BigDecimal getTotalA() {
		return totalA;
	}
	public void setTotalA(BigDecimal totalA) {
		this.totalA = totalA;
	}
	public Date getDateInvoice() {
		return dateInvoice;
	}
	public void setDateInvoice(Date dateInvoice) {
		this.dateInvoice = dateInvoice;
	}
	public InvoiceSupplierView(int id, BigDecimal total, Date date, int month, int idInvoice, int discount,
			BigDecimal condition, BigDecimal totalInvoice, BigDecimal iLAPay, BigDecimal amountReceived,
			BigDecimal totalA,Date dateInvoice) {
		super();
		this.id = id;
		this.total = total;
		this.date = date;
		this.month = month;
		this.idInvoice = idInvoice;
		this.discount = discount;
		this.condition = condition;
		this.totalInvoice = totalInvoice;
		this.iLAPay = iLAPay;
		this.amountReceived = amountReceived;
		this.totalA = totalA;
		this.dateInvoice=dateInvoice;
	}
	public InvoiceSupplierView() {
		super();
		// TODO Auto-generated constructor stub
	}
}
