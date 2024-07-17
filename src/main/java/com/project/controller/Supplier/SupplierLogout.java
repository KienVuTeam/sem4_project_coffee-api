package com.project.controller.Supplier;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.utils.StringValue;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/Supplier")
public class SupplierLogout {

	@GetMapping("/LogOutAccount")
	public String usersLogoutAccount(HttpServletResponse response) {
		Cookie suppCookie = new Cookie(StringValue.nameCookieSupplier, null);
		suppCookie.setMaxAge(0);
		suppCookie.setPath("/");
		response.addCookie(suppCookie);
		//
		String urlRedirect = "redirect:" + StringValue.baseURL;
		return urlRedirect;
	}
}
