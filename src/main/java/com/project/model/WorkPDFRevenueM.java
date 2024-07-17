package com.project.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class WorkPDFRevenueM {
	private int idInvoice;
	private String codeInvoice;
	private int idSupplier;
	private String supplierName;
	private Date orderDate;
	private BigDecimal sumProfitsS;
	private BigDecimal sumProfitsA; //All
	
	private List<WorkPDFRevenueS> lsWorkSub;

	public int getIdInvoice() {
		return idInvoice;
	}
	public void setIdInvoice(int idInvoice) {
		this.idInvoice = idInvoice;
	}
	public String getCodeInvoice() {
		return codeInvoice;
	}
	public void setCodeInvoice(String codeInvoice) {
		this.codeInvoice = codeInvoice;
	}		
	public int getIdSupplier() {
		return idSupplier;
	}
	public void setIdSupplier(int idSupplier) {
		this.idSupplier = idSupplier;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public BigDecimal getSumProfitsS() {
		return sumProfitsS;
	}
	public void setSumProfitsS(BigDecimal sumProfitsS) {
		this.sumProfitsS = sumProfitsS;
	}
	public BigDecimal getSumProfitsA() {
		return sumProfitsA;
	}
	public void setSumProfitsA(BigDecimal sumProfitsA) {
		this.sumProfitsA = sumProfitsA;
	}
	public List<WorkPDFRevenueS> getLsWorkSub() {
		return lsWorkSub;
	}
	public void setLsWorkSub(List<WorkPDFRevenueS> lsWorkSub) {
		this.lsWorkSub = lsWorkSub;
	}
}
