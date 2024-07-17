package com.project.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Voucher {
	private String id;
	private BigDecimal condition;
	private int discount;
	private String usercreate;
	private Date startDate;
	private Date endDate;
	private int isActive;
	private int used;
	private int idenVou;

	public Voucher(String id, BigDecimal condition, int discount, String usercreate, Date startDate, Date endDate,
			int isActive, int used) {
		super();
		this.id = id;
		this.condition = condition;
		this.discount = discount;
		this.usercreate = usercreate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isActive = isActive;
		this.used = used;
	}

	public int getUsed() {
		return used;
	}

	public void setUsed(int used) {
		this.used = used;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getCondition() {
		return condition;
	}

	public void setCondition(BigDecimal condition) {
		this.condition = condition;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getUsercreate() {
		return usercreate;
	}

	public void setUsercreate(String usercreate) {
		this.usercreate = usercreate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public Voucher() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdenVou() {
		return idenVou;
	}

	public void setIdenVou(int idenVou) {
		this.idenVou = idenVou;
	}
	
	

}
