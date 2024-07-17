package com.project.modelview;

import java.sql.Date;

public class BlogView {
	private int id;
	private int userCreate;
	private String title;
	private String image;
	private String description;
	private Date createDate;
	private int idAccount;
	private int isStatus;
	private int idSupp;
	private String nameSupp;
	private String userType;
	private int countComment;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserCreate() {
		return userCreate;
	}
	public void setUserCreate(int userCreate) {
		this.userCreate = userCreate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getIdAccount() {
		return idAccount;
	}
	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}
	public int getIsStatus() {
		return isStatus;
	}
	public void setIsStatus(int isStatus) {
		this.isStatus = isStatus;
	}
	public int getIdSupp() {
		return idSupp;
	}
	public void setIdSupp(int idSupp) {
		this.idSupp = idSupp;
	}
	public String getNameSupp() {
		return nameSupp;
	}
	public void setNameSupp(String nameSupp) {
		this.nameSupp = nameSupp;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public int getCountComment() {
		return countComment;
	}
	public void setCountComment(int countComment) {
		this.countComment = countComment;
	}
	
}
