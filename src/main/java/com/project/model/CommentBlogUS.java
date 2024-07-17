package com.project.model;

import java.sql.Date;
import java.util.List;

public class CommentBlogUS {
	private int id;
	private String comment;
	private int timeSpace;
	private Date dateCreate;
	private int idAccount;
	private String userName;
	private List<SubCommentBlogUS> lsSubComment;
	private int countSubC;
	
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
	public List<SubCommentBlogUS> getLsSubComment() {
		return lsSubComment;
	}
	public void setLsSubComment(List<SubCommentBlogUS> lsSubComment) {
		this.lsSubComment = lsSubComment;
	}
	public int getCountSubC() {
		return countSubC;
	}
	public void setCountSubC(int countSubC) {
		this.countSubC = countSubC;
	}
	
	
	
	
}
