package com.project.modelview;

import java.sql.Date;
import java.util.List;

public class CommentBlogView {
	private int id;
	private String idAccount;
	private int idBlog;
	private String Comment;
	private Date DateCreate;
	private int IndC;
	private int MnC;
	private String UserName;
	private int status;
	private String UserNameRep;
	public List<CommentBlogView> subComment;

	public CommentBlogView() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommentBlogView(int id, String idAccount, int idBlog, String comment, Date dateCreate, int indC, int mnC,
			String userName, int status, String idAccountRep, String UserNameRep) {
		super();
		this.id = id;
		this.idAccount = idAccount;
		this.idBlog = idBlog;
		Comment = comment;
		DateCreate = dateCreate;
		IndC = indC;
		MnC = mnC;
		UserName = userName;
		this.status = status;
		this.UserNameRep=UserNameRep;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(String idAccount) {
		this.idAccount = idAccount;
	}

	public int getIdBlog() {
		return idBlog;
	}

	public void setIdBlog(int idBlog) {
		this.idBlog = idBlog;
	}

	public String getComment() {
		return Comment;
	}

	public void setComment(String comment) {
		Comment = comment;
	}

	public Date getDateCreate() {
		return DateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		DateCreate = dateCreate;
	}

	public int getIndC() {
		return IndC;
	}

	public void setIndC(int indC) {
		IndC = indC;
	}

	public int getMnC() {
		return MnC;
	}

	public void setMnC(int mnC) {
		MnC = mnC;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUserNameRep() {
		return UserNameRep;
	}

	public void setUserNameRep(String userNameRep) {
		UserNameRep = userNameRep;
	}

}
