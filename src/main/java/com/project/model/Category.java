package com.project.model;

import java.sql.Date;

public class Category {
	private int id;
	private String title;
	private Date createDate;
	private boolean isActive;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public Category(int id, String title, Date createDate, boolean isActive) {
		super();
		this.id = id;
		this.title = title;
		this.createDate = createDate;
		this.isActive = isActive;
	}
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
