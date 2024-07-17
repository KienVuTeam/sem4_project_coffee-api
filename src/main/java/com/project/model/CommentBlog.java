package com.project.model;

import java.sql.Date;

public class CommentBlog {
	private int id;
	private int idBlog;
	private String idAccount;
	private String comment;
	private Date dateCreate;
	private int indC;
	private int mnC;
	private int status;
	private String idAccountRep;
	private int countCMT;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdBlog() {
		return idBlog;
	}

	public void setIdBlog(int idBlog) {
		this.idBlog = idBlog;
	}

	public String getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(String idAccount) {
		this.idAccount = idAccount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public int getIndC() {
		return indC;
	}

	public void setIndC(int indC) {
		this.indC = indC;
	}

	public int getMnC() {
		return mnC;
	}

	public void setMnC(int mnC) {
		this.mnC = mnC;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getIdAccountRep() {
		return idAccountRep;
	}

	public void setIdAccountRep(String idAccountRep) {
		this.idAccountRep = idAccountRep;
	}

	public int getCountCMT() {
		return countCMT;
	}

	public void setCountCMT(int countCMT) {
		this.countCMT = countCMT;
	}

	public CommentBlog(int id, int idBlog, String idAccount, String comment, Date dateCreate, int indC, int mnC,
			int status, String idAccountRep,int countCMT) {
		super();
		this.id = id;
		this.idBlog = idBlog;
		this.idAccount = idAccount;
		this.comment = comment;
		this.dateCreate = dateCreate;
		this.indC = indC;
		this.mnC = mnC;
		this.status = status;
		this.idAccountRep = idAccountRep;
		this.countCMT = countCMT;
	}

	public CommentBlog() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
