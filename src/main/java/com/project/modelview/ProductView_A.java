package com.project.modelview;

import java.math.BigDecimal;

public class ProductView_A {
	private int id;
	private String title;
	private String image;
	private String image1;
	private String image2;
	private String image3;
	private String description;
	private BigDecimal price;
	private int isActive;
	private int idcate;
	private int idSupplier;
	
	//category
	private String nameCate;
	
	//supplier
	private String nameSupp;

	//discount
	private BigDecimal discount;
	private BigDecimal finalPrice;
	
	//sold
	private int sold;
	
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
	public int getIdSupplier() {
		return idSupplier;
	}
	public void setIdSupplier(int idSupplier) {
		this.idSupplier = idSupplier;
	}
	public String getNameCate() {
		return nameCate;
	}
	public void setNameCate(String nameCate) {
		this.nameCate = nameCate;
	}
	public String getNameSupp() {
		return nameSupp;
	}
	public void setNameSupp(String nameSupp) {
		this.nameSupp = nameSupp;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public BigDecimal getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
	}
	
	public int getSold() {
		return sold;
	}
	public void setSold(int sold) {
		this.sold = sold;
	}
	public ProductView_A(int id, String title, String image, String image1, String image2, String image3,
			String description, BigDecimal price, int isActive, int idcate, int idSupplier, String nameCate,
			String nameSupp, BigDecimal discount, BigDecimal finalPrice, int sold) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
		this.description = description;
		this.price = price;
		this.isActive = isActive;
		this.idcate = idcate;
		this.idSupplier = idSupplier;
		this.nameCate = nameCate;
		this.nameSupp = nameSupp;
		this.discount = discount;
		this.finalPrice = finalPrice;
		this.sold=sold;
	}
	public ProductView_A() {
		super();
		// TODO Auto-generated constructor stub
	}
}

