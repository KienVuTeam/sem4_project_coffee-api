package com.project.modelview;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MVNewBlog {
	private int id;
	private String blogTitle;
	private String blogImage;
	private String blogDescription;
	@JsonFormat(pattern="dd--MM--yyyy")
	private Date dateCreate;
	private int userCreate;
	private int isStatus;
	
	@JsonCreator
    public MVNewBlog(@JsonProperty("id") int id, @JsonProperty("title") String title,
    		@JsonProperty("description") String description, @JsonProperty("image") String image,
    		@JsonProperty("createDate") Date createDate, @JsonProperty("isStatus") int isStatus) {
        this.id =id;
        this.blogTitle =title;
        this.blogImage =image;
        this.blogDescription=description;
        this.dateCreate = createDate;
        this.isStatus =isStatus;
        
    }
	public MVNewBlog() {}
	
	

	public MVNewBlog(int id, String blogTitle, String blogImage, String blogDescription, Date dateCreate,
			int userCreate, int isStatus) {
		super();
		this.id = id;
		this.blogTitle = blogTitle;
		this.blogImage = blogImage;
		this.blogDescription = blogDescription;
		this.dateCreate = dateCreate;
		this.userCreate = userCreate;
		this.isStatus = isStatus;
	}
	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getBlogImage() {
		return blogImage;
	}

	public void setBlogImage(String blogImage) {
		this.blogImage = blogImage;
	}

	public String getBlogDescription() {
		return blogDescription;
	}

	public void setBlogDescription(String blogDescription) {
		this.blogDescription = blogDescription;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public int getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(int userCreate) {
		this.userCreate = userCreate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIsStatus() {
		return isStatus;
	}
	public void setIsStatus(int isStatus) {
		this.isStatus = isStatus;
	}
	
	
	
	
	
	
	
}
