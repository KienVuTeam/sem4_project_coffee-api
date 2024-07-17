package com.project.model;

import java.sql.Date;

public class ReviewViewS {
	private int idProduct;
	private String nameProd;
	private float avgReview;
	private int id;
	private int review;
	private int countReview;
	private Date createDate;
	private String nameCus;
	private float avgAllReview;
	private float countAllReview;
	
	public ReviewViewS(int idProduct, String nameProd, float avgReview, int id, int review, int countReview,
			Date createDate, String nameCus, float avgAllReview, float countAllReview) {
		super();
		this.idProduct = idProduct;
		this.nameProd = nameProd;
		this.avgReview = avgReview;
		this.id = id;
		this.review = review;
		this.countReview = countReview;
		this.createDate = createDate;
		this.nameCus = nameCus;
		this.avgAllReview = avgAllReview;
		this.countAllReview = countAllReview;
	}
	public float getAvgAllReview() {
		return avgAllReview;
	}
	public void setAvgAllReview(float avgAllReview) {
		this.avgAllReview = avgAllReview;
	}
	public float getCountAllReview() {
		return countAllReview;
	}
	public void setCountAllReview(float countAllReview) {
		this.countAllReview = countAllReview;
	}
	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	public String getNameProd() {
		return nameProd;
	}
	public void setNameProd(String nameProd) {
		this.nameProd = nameProd;
	}
	public float getAvgReview() {
		return avgReview;
	}
	public void setAvgReview(float avgReview) {
		this.avgReview = avgReview;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getReview() {
		return review;
	}
	public void setReview(int review) {
		this.review = review;
	}
	public int getCountReview() {
		return countReview;
	}
	public void setCountReview(int countReview) {
		this.countReview = countReview;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getNameCus() {
		return nameCus;
	}
	public void setNameCus(String nameCus) {
		this.nameCus = nameCus;
	}
	public ReviewViewS() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
