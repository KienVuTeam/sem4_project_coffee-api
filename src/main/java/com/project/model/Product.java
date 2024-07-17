package com.project.model;

import java.math.BigDecimal;

public class Product {
	private int id;
	private String title;
	private String image;
	private String image1;
	private String image2;
	private String image3;
	private String description;
	private BigDecimal price;
	private int idSupplier;
	private int isActive;
	private int idcate;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public String getImage3() {
		return image3;
	}
	public void setImage3(String image3) {
		this.image3 = image3;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getIdSupplier() {
		return idSupplier;
	}
	public void setIdSupplier(int idSupplier) {
		this.idSupplier = idSupplier;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public int getIdcate() {
		return idcate;
	}
	public void setIdcate(int idcate) {
		this.idcate = idcate;
	}
	public Product(int id, String title, String image, String image1, String image2, String image3, String description,
			BigDecimal price, int idSupplier, int isActive, int idcate) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
		this.description = description;
		this.price = price;
		this.idSupplier = idSupplier;
		this.isActive = isActive;
		this.idcate = idcate;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
