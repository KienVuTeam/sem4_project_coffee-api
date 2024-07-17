package com.project.model;

public class Admin {
	private int id;
	private String username;
	private String password;
	private boolean flagLogin;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isFlagLogin() {
		return flagLogin;
	}
	public void setFlagLogin(boolean flagLogin) {
		this.flagLogin = flagLogin;
	}
	public Admin(int id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
