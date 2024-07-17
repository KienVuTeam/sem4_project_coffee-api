package com.project.controller.Supplier;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.model.Supplier;
import com.project.others.Encode_Decode;
import com.project.others.Encode_Decode;
import com.project.repository.ilaProjectCoffeeRepository;
import com.project.utils.StringValue;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/Supplier/Login")
public class SupplierLogin {

	@GetMapping("")
	public String supplierLogin(Model model) {
		model.addAttribute("modeForm", "Login");
		return "/Supplier/SupplierRAndLogin";
	}
	
	@GetMapping("/supplierSubmitLogin")
	@ResponseBody
	public boolean userLoginAccount(String userName, String userPassword,HttpServletResponse response) {
		Supplier acLogin = ilaProjectCoffeeRepository.getInstance().supplierLoginAccount(userName, userPassword);
		if (acLogin.isFlagLogin()) {
			/**
			 * @Success Login & Create Cookie
			 */
			int cookieExpiry = 86400; 
			
			Cookie idSuppCookie = new Cookie(StringValue.nameCookieSupplier,String.valueOf(acLogin.getId()));
			idSuppCookie.setMaxAge(cookieExpiry);
			idSuppCookie.setPath("/");
			response.addCookie(idSuppCookie);
			
			Cookie avtSuppCookie = new Cookie("avtSuppCookie",String.valueOf(acLogin.getAvatar()));
			avtSuppCookie.setMaxAge(cookieExpiry);
			avtSuppCookie.setPath("/");
			response.addCookie(avtSuppCookie);
			
			Cookie nameSuppCookie = new Cookie("nameSuppCookie", Encode_Decode.getInstance().encodeString(acLogin.getTitle()));
			nameSuppCookie.setMaxAge(cookieExpiry);
			nameSuppCookie.setPath("/");
			response.addCookie(nameSuppCookie);
			
			Cookie statusSuppCookie = new Cookie("statusSuppCookie",String.valueOf(acLogin.getIsActive()));
			statusSuppCookie.setMaxAge(cookieExpiry);
			statusSuppCookie.setPath("/");
			response.addCookie(statusSuppCookie);
			
			String supplier = Encode_Decode.getInstance().objToJsonString(acLogin);
			Cookie SuppCookie = new Cookie("suppCookie", Encode_Decode.getInstance().encodeString(supplier));
			SuppCookie.setMaxAge(cookieExpiry);
			SuppCookie.setPath("/");
			response.addCookie(SuppCookie);
			/**
			 * @Add Batch In Here
			 */
			ilaProjectCoffeeRepository.getInstance().autoStartDiscount(acLogin.getId());
			ilaProjectCoffeeRepository.getInstance().autoEndDiscount(acLogin.getId());
			ilaProjectCoffeeRepository.getInstance().autoStartVoucherType(String.valueOf(acLogin.getId()));
			ilaProjectCoffeeRepository.getInstance().autoEndVoucherType(String.valueOf(acLogin.getId()));
		}
		return acLogin.isFlagLogin();
	}
}
