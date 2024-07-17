package com.project.controller.Clients;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.model.ObjectMessage;
import com.project.model.menuUsers;
import com.project.others.CheckCookieUsers;
import com.project.utils.StringValue;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UsersPageController {
	
	@GetMapping("")
	public String HomePage(HttpServletRequest request ,Model model) {
		menuUsers mU = new menuUsers();
		mU.setHomePage(true);
		mU.setFlagLogin(CheckCookieUsers.getInstance().checkCookieExists(request, StringValue.nameCookieUser));
		//
		model.addAttribute("menuUsers", mU);
		return "/Users/UsersHomePage";
	}
	
	@GetMapping("/UsersLogin")
	public String usersPageLogin(Model model) {
		model.addAttribute("modeForm", "Login");
		return "/Users/UsersLoginAndR";
	}
	
	@GetMapping("/UsersRegister")
	public String usersPageRegister(Model model) {
		model.addAttribute("modeForm", "Register");
		return "/Users/UsersLoginAndR";
	}
	
	@GetMapping("/LogOutAccount")
	public String usersLogoutAccount(HttpServletResponse response) {
		Cookie usersCookie = new Cookie("idUsers","0");
		Cookie usersCookieStatus = new Cookie("usersStatus","");
		usersCookie.setMaxAge(0);
		usersCookieStatus.setMaxAge(0);
		//
		usersCookie.setPath("/");
		usersCookieStatus.setPath("/");
		//
		response.addCookie(usersCookie);
		response.addCookie(usersCookieStatus);
		String urlRedirect = "redirect:" + StringValue.baseURL;
		return urlRedirect;
	}
}
