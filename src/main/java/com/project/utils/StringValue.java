package com.project.utils;

import java.util.ArrayList;
import java.util.List;

public class StringValue {
	public static final String nameCookieSupplier = "idSupp";
	public static final String nameCookieUser = "idUsers";
	public static final String nameCookieAdmin = "idAd";
	
	// dashBoard
	public static final String dashBoardAdmin = "/Admin/dashboard";
	public static final String dashBoardSupplier = "/Supplier/Dashboard";
	
	public static final String baseURL = "http://localhost:8080";
	
	// redirect
	public static final String redirectULogin = "/UsersLogin";
	public static final String redirectUProfile = "/Profile/";
	public static final String redirectSLogin = "/Supplier/Login";
	public static final String redirectALogin = "/Admin/Login";
	
	public static List<String> headerOutputRevenue(){
		List<String> lsExHeader = new ArrayList<>();
		lsExHeader.add("Invoice Code");
		lsExHeader.add("Customers Name");
		lsExHeader.add("Address");
		lsExHeader.add("Phone");
		lsExHeader.add("OrderDate");
		lsExHeader.add("Quantity");
		lsExHeader.add("Total Orders");
		lsExHeader.add("Profits");
		return lsExHeader;
	}
		
}
