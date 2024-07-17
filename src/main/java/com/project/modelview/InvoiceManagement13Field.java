package com.project.modelview;

import java.math.BigDecimal;
import java.util.Date;

public class InvoiceManagement13Field {

	private int idInvoice;
	private int idInvoiceSupp; //idInvoiceSupp
	private String nameCus;
	private String address;
	private Date createAt;
	private Date dateMaxC;
	private int statusOfInvoice;
	private int statusOfInvoiceSupp;
	private BigDecimal priceVoucher;
	private BigDecimal estimatedTotalPrice;
	private BigDecimal totalOrderAmount;
	private BigDecimal refundtoCustomers;
	private BigDecimal totalAmountOfProduct;
	private BigDecimal moneyReceived;
	private String voucher;
	private BigDecimal commission;
	
	public InvoiceManagement13Field() {};
	public InvoiceManagement13Field(int idInvoice, int idInvoiceSupp, String nameCus, String address, Date createAt,
			int statusOfInvoice, int statusOfInvoiceSupp, BigDecimal priceVoucher, BigDecimal estimatedTotalPrice,
			BigDecimal totalOrderAmount, BigDecimal refundtoCustomers, BigDecimal totalAmountOfProduct, String voucher,
			BigDecimal commission, BigDecimal moneyReceived, Date dateMaxC) {
		super();
		this.idInvoice = idInvoice;
		this.idInvoiceSupp = idInvoiceSupp;
		this.nameCus = nameCus;
		this.address = address;
		this.createAt = createAt;
		this.statusOfInvoice = statusOfInvoice;
		this.statusOfInvoiceSupp = statusOfInvoiceSupp;
		this.priceVoucher = priceVoucher;
		this.estimatedTotalPrice = estimatedTotalPrice;
		this.totalOrderAmount = totalOrderAmount;
		this.refundtoCustomers = refundtoCustomers;
		this.totalAmountOfProduct = totalAmountOfProduct;
		this.voucher = voucher;
		this.commission = commission;
		this.moneyReceived = moneyReceived;
		this.dateMaxC = dateMaxC;
	}
	public int getIdInvoice() {
		return idInvoice;
	}
	public void setIdInvoice(int idInvoice) {
		this.idInvoice = idInvoice;
	}
	public int getIdInvoiceSupp() {
		return idInvoiceSupp;
	}
	public void setIdInvoiceSupp(int idInvoiceSupp) {
		this.idInvoiceSupp = idInvoiceSupp;
	}
	public String getNameCus() {
		return nameCus;
	}
	public void setNameCus(String nameCus) {
		this.nameCus = nameCus;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public int getStatusOfInvoice() {
		return statusOfInvoice;
	}
	public void setStatusOfInvoice(int statusOfInvoice) {
		this.statusOfInvoice = statusOfInvoice;
	}
	public int getStatusOfInvoiceSupp() {
		return statusOfInvoiceSupp;
	}
	public void setStatusOfInvoiceSupp(int statusOfInvoiceSupp) {
		this.statusOfInvoiceSupp = statusOfInvoiceSupp;
	}
	public BigDecimal getPriceVoucher() {
		return priceVoucher;
	}
	public void setPriceVoucher(BigDecimal priceVoucher) {
		this.priceVoucher = priceVoucher;
	}
	public BigDecimal getEstimatedTotalPrice() {
		return estimatedTotalPrice;
	}
	public void setEstimatedTotalPrice(BigDecimal estimatedTotalPrice) {
		this.estimatedTotalPrice = estimatedTotalPrice;
	}
	public BigDecimal getTotalOrderAmount() {
		return totalOrderAmount;
	}
	public void setTotalOrderAmount(BigDecimal totalOrderAmount) {
		this.totalOrderAmount = totalOrderAmount;
	}
	public BigDecimal getRefundtoCustomers() {
		return refundtoCustomers;
	}
	public void setRefundtoCustomers(BigDecimal refundtoCustomers) {
		this.refundtoCustomers = refundtoCustomers;
	}
	public BigDecimal getTotalAmountOfProduct() {
		return totalAmountOfProduct;
	}
	public void setTotalAmountOfProduct(BigDecimal totalAmountOfProduct) {
		this.totalAmountOfProduct = totalAmountOfProduct;
	}
	public String getVoucher() {
		return voucher;
	}
	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}
	public BigDecimal getCommission() {
		return commission;
	}
	public Date getDateMaxC() {
		return dateMaxC;
	}
	public void setDateMaxC(Date dateMaxC) {
		this.dateMaxC = dateMaxC;
	}
	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}
	public BigDecimal getMoneyReceived() {
		return moneyReceived;
	}
	public void setMoneyReceived(BigDecimal moneyReceived) {
		this.moneyReceived = moneyReceived;
	}
	
	
	
}
