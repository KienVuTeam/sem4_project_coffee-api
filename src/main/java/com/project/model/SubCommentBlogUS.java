package com.project.model;

import java.sql.Date;

public class SubCommentBlogUS {
	private int id;
	private String comment;
	private int timeSpace;
	private Date dateCreate;
	private int idAccount;
	private String userName;
	private String userReply;
	private int userType;
	// formMain
	private int idMainC;
	private int idBlog;
	private int idReply;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getTimeSpace() {
		return timeSpace;
	}

	public void setTimeSpace(int timeSpace) {
		this.timeSpace = timeSpace;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public int getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserReply() {
		return userReply;
	}

	public void setUserReply(String userReply) {
		this.userReply = userReply;
	}

	public int getIdMainC() {
		return idMainC;
	}

	public void setIdMainC(int idMainC) {
		this.idMainC = idMainC;
	}

	public int getIdBlog() {
		return idBlog;
	}

	public void setIdBlog(int idBlog) {
		this.idBlog = idBlog;
	}

	public int getIdReply() {
		return idReply;
	}

	public void setIdReply(int idReply) {
		this.idReply = idReply;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}
	
	
}