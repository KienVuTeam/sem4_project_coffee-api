package com.project.modelview;

import java.math.BigDecimal;
import java.sql.Date;

public class VoucherView {
	private String id;
	private BigDecimal condition;
	private int discount;
	private String usercreate;
	private Date startDate;
	private Date endDate;
	private boolean isActive;
	//View
	private int dayLeft;

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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getDayLeft() {
		return dayLeft;
	}

	public void setDayLeft(int dayLeft) {
		this.dayLeft = dayLeft;
	}
	
	
	
	
	
}

