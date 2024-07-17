package com.project.modelview;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


public class MVOrderManagement {

	private int id;
	private String nameCus;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date createDate;
	private int isStatus;
	private String voucherS;
	private String voucherA;
	private String totalPrice;
	
	public MVOrderManagement() {}

	public MVOrderManagement(int id, String nameCus, Date createDate, int isStatus, String voucherS, String voucherA,
			String totalPrice) {
		super();
		this.id = id;
		this.nameCus = nameCus;
		this.createDate = createDate;
		this.isStatus = isStatus;
		this.voucherS = voucherS;
		this.voucherA = voucherA;
		this.totalPrice = totalPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameCus() {
		return nameCus;
	}

	public void setNameCus(String nameCus) {
		this.nameCus = nameCus;
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

	public String getVoucherS() {
		return voucherS;
	}

	public void setVoucherS(String voucherS) {
		this.voucherS = voucherS;
	}

	public String getVoucherA() {
		return voucherA;
	}

	public void setVoucherA(String voucherA) {
		this.voucherA = voucherA;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	

	




	
}
