package com.project.controller.Clients;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.calling.AccountCalling;
import com.project.model.Account;
import com.project.repository.ilaProjectCoffeeRepository;
import com.project.utils.StringValue;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/Login")
public class LoginController {
	
	@GetMapping("/userSubmitLogin")
	@ResponseBody
	public boolean userLoginAccount(String userName, String userPassword,HttpServletResponse response) {
		Account acLogin = ilaProjectCoffeeRepository.getInstance().userLoginAccount(userName, userPassword);
		if (acLogin.isFlagLogin()) {
			/**
			 * @Success Login
			 */
			int cookieExpiry = 86400; 
			Cookie usersCookie = new Cookie(StringValue.nameCookieUser,String.valueOf(acLogin.getId()));
			usersCookie.setMaxAge(cookieExpiry);
			usersCookie.setPath("/");
			response.addCookie(usersCookie);
			//
			ilaProjectCoffeeRepository.getInstance().autoStartEndDiscountUsers();
			ilaProjectCoffeeRepository.getInstance().autoUpdateItemInCart(acLogin.getId());
		}
		return acLogin.isFlagLogin();
	}
	
	@GetMapping("/checkExCookieUserLogin")
	@ResponseBody
	public boolean checkUsersLoginCookie(@CookieValue(value = StringValue.nameCookieUser , defaultValue = "0") int idUsers) {
		boolean flagCheck = false;
		if (idUsers != 0) {
			return true;
		}
		return flagCheck;
	}
	
	@GetMapping("/checkStatusOfAccount")
	@ResponseBody
	public boolean checkUsersStatusCookie(@CookieValue(value = StringValue.nameCookieUser , defaultValue = "0") int idUsers) {
		return ilaProjectCoffeeRepository.getInstance().usersCheckStatusAccount(idUsers);
	}
	
}
