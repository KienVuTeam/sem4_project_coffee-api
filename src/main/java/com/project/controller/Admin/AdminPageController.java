package com.project.controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.model.Admin;
import com.project.repository.ilaProjectCoffeeRepository;
import com.project.utils.StringValue;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/Admin")
public class AdminPageController {
	
	
	@GetMapping("/Login")
	public String adminLoginPage() {
		return "/Admin/AdminLogin";
	}
	
	@GetMapping("/adminLoginToAccount")
	@ResponseBody
	public boolean adminLoginToAccount(String userName, String password, HttpServletResponse response) {
		Admin acLogin = ilaProjectCoffeeRepository.getInstance().adminLoginAccount(userName, password);
		if (acLogin.isFlagLogin()) {
			/**
			 * @Success Login
			 */
			int cookieExpiry = 86400; 
			Cookie usersCookie = new Cookie(StringValue.nameCookieAdmin,"0");
			usersCookie.setMaxAge(cookieExpiry);
			usersCookie.setPath("/");
			response.addCookie(usersCookie);
			/**
			 * @Add Batch In Here
			 */
			ilaProjectCoffeeRepository.getInstance().autoStartVoucherType("0");
			ilaProjectCoffeeRepository.getInstance().autoEndVoucherType("0");
			ilaProjectCoffeeRepository.getInstance().AD_autoCheckSupplierNotConfirmA();
			ilaProjectCoffeeRepository.getInstance().AD_autoCheckConfirmA();
			return true;
		}
		return false;
	}
	

}
