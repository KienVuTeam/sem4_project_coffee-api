package com.project.modelview;


import java.math.BigDecimal;

public class ProductView {
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
	//VIEW
	private int idCate;
	private String cateName;
	private BigDecimal finalPRice;
	private int saleNumbers;
	private String supplierName;
	private String supplierAvatar;
	//Category
	private String nameCate;
	//DisCount
	private BigDecimal discount;
	private BigDecimal finalPrice;
	private int isStatus;
	
	public BigDecimal getFinalPRice() {
		return finalPRice;
	}
	public void setFinalPRice(BigDecimal finalPRice) {
		this.finalPRice = finalPRice;
	}
	public String getNameCate() {
		return nameCate;
	}
	public void setNameCate(String nameCate) {
		this.nameCate = nameCate;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public int getIsStatus() {
		return isStatus;
	}
	public void setIsStatus(int isStatus) {
		this.isStatus = isStatus;
	}
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
	
	public BigDecimal getFinalPrice() {
		return finalPRice;
	}
	public void setFinalPrice(BigDecimal finalPRice) {
		this.finalPRice = finalPRice;
	}
	public int getIdSupplier() {
		return idSupplier;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
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
	public int getIdCate() {
		return idCate;
	}
	public void setIdCate(int idCate) {
		this.idCate = idCate;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public int getSaleNumbers() {
		return saleNumbers;
	}
	public void setSaleNumbers(int saleNumbers) {
		this.saleNumbers = saleNumbers;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierAvatar() {
		return supplierAvatar;
	}
	public void setSupplierAvatar(String supplierAvatar) {
		this.supplierAvatar = supplierAvatar;
	}
	
	
	
}

