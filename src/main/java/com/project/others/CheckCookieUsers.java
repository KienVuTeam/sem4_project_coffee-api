package com.project.others;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class CheckCookieUsers {
	private static CheckCookieUsers instance = null;
	
	public static CheckCookieUsers getInstance() {
		if (instance == null) {
			instance = new CheckCookieUsers();
		}
		return instance;
	}
	
	public boolean checkCookieExists(HttpServletRequest request,String cookieName) {
		boolean flagExists = false;
		try {
			if (request.getCookies() == null) {
				return false;
			}
			//
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equals(cookieName)) {
					return true;
				} 
			}
		} catch (Exception e) {
			System.out.println("ERROR checkCookieExists");
			System.out.println(e.getMessage());
		}
		return flagExists;
	}
}
