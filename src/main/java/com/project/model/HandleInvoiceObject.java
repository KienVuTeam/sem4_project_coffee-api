package com.project.model;

import java.math.BigDecimal;

public class HandleInvoiceObject {
	public int id;
	public int idUser;
	public String lsCartSelect;
	public String voucherAdmin;
	public String lsVoucherS;
	public int idAddress;
	public BigDecimal feeService;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getLsCartSelect() {
		return lsCartSelect;
	}
	public void setLsCartSelect(String lsCartSelect) {
		this.lsCartSelect = lsCartSelect;
	}
	public String getVoucherAdmin() {
		return voucherAdmin;
	}
	public void setVoucherAdmin(String voucherAdmin) {
		this.voucherAdmin = voucherAdmin;
	}
	public String getLsVoucherS() {
		return lsVoucherS;
	}
	public void setLsVoucherS(String lsVoucherS) {
		this.lsVoucherS = lsVoucherS;
	}
	public int getIdAddress() {
		return idAddress;
	}
	public void setIdAddress(int idAddress) {
		this.idAddress = idAddress;
	}
	public BigDecimal getFeeService() {
		return feeService;
	}
	public void setFeeService(BigDecimal feeService) {
		this.feeService = feeService;
	}
	
		
}
