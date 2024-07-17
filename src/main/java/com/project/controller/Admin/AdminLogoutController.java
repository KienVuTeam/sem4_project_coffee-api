package com.project.controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.utils.StringValue;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
@Controller
@RequestMapping("/AdminLogout")
public class AdminLogoutController {
	@GetMapping("")
	public String adminLogoutAccount(HttpServletResponse response) {
		Cookie usersCookie = new Cookie(StringValue.nameCookieAdmin,"0");
		usersCookie.setMaxAge(0);
		//
		response.addCookie(usersCookie);
		//
		usersCookie.setPath("/");
		String urlRedirect = "redirect:" + StringValue.baseURL;
		return urlRedirect;
	}
}
