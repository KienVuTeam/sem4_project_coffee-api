package com.project.utils;

import java.util.HashMap;
import java.util.Map;

public class MappingUrls {

	private static MappingUrls instance = null;
	public static MappingUrls getInstance() {
		if (instance == null) {
			instance = new MappingUrls();
			addDictionUsersCheckingUrls();
			addDitionUsersAfterLoginUrls();
		}
		return instance;
	}
	
	/**
	 * String is Urls,
	 * Boolean is Need to Check Or Not: True is Check And False is Not Check
	 */
	public static final Map<String, Boolean> userDictionary  = new HashMap<>();

	private static void addDictionUsersCheckingUrls() {
		
	}	
	
	public static final Map<String, Boolean> usersAfterLogin = new HashMap<>();
	
	private static void addDitionUsersAfterLoginUrls() {
		usersAfterLogin.put("/Login/userSubmitLogin", true);
		usersAfterLogin.put("/UsersRegister", true);
		usersAfterLogin.put("/UsersLogin", true);
		
	}
	
}
