package com.project.model;

import java.math.BigDecimal;

public class SupplierRefundV {

	private int id;
	private int idSupplier;
	private int idInvoice;
	private int idInvoiceD;
	private BigDecimal priceRefund;
	private int status;
	public SupplierRefundV(int id, int idSupplier, int idInvoice, int idInvoiceD, BigDecimal priceRefund, int status) {
		super();
		this.id = id;
		this.idSupplier = idSupplier;
		this.idInvoice = idInvoice;
		this.idInvoiceD = idInvoiceD;
		this.priceRefund = priceRefund;
		this.status = status;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public int getIdInvoice() {
		return idInvoice;
	}
	public void setIdInvoice(int idInvoice) {
		this.idInvoice = idInvoice;
	}
	public int getIdInvoiceD() {
		return idInvoiceD;
	}
	public void setIdInvoiceD(int idInvoiceD) {
		this.idInvoiceD = idInvoiceD;
	}
	public BigDecimal getPriceRefund() {
		return priceRefund;
	}
	public void setPriceRefund(BigDecimal priceRefund) {
		this.priceRefund = priceRefund;
	}
	public SupplierRefundV() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
