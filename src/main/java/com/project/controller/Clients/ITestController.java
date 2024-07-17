package com.project.controller.Clients;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.model.HandleStatusObject;
import com.project.repository.ilaProjectCoffeeRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/dll")
public class ITestController {
	
	@GetMapping("/AdInvoice")
	public String adminInvoice(int idInvoice,Model model) {
		model.addAttribute("lsInvoice", ilaProjectCoffeeRepository.getInstance().dllAdminInvoice(idInvoice));
		return "dllAdminInvoice";
	}
	
	@GetMapping("/SuppInvoice")
	public String supplierInvoice(int idInvoice,int idSupplier,Model model) {
		model.addAttribute("lsInvoice", ilaProjectCoffeeRepository.getInstance().dllSupplierInvoice(idInvoice, idSupplier));
		return "/dllSupplierInvoice";
	}
	
	@GetMapping("/typeStatus")
	@ResponseBody
	public void testConfirm(int idInvoice,int idInvoiceD,int idSupplier,int idUser,int userCase,int statusType) {
		HandleStatusObject hdlO = new HandleStatusObject();
		hdlO.setIdInvoice(idInvoice);
		hdlO.setIdInvoiceDetails(idInvoiceD);
		hdlO.setIdUser(2);
		hdlO.setUserCase(userCase);
		hdlO.setStatusType(statusType);
		hdlO.setIdSupplier(idSupplier);
		ilaProjectCoffeeRepository.getInstance().userConfirmInvoiceDetails(hdlO);
	}
	
	@GetMapping("/createCookieSupplier")
	@ResponseBody
	public String createCookieSupplier(HttpServletResponse response) {
		int cookieExpiry = 86400; 
		Cookie supplierCookie = new Cookie("idSupp", String.valueOf(1));
		supplierCookie.setMaxAge(cookieExpiry);
		supplierCookie.setPath("/");
		response.addCookie(supplierCookie);
		return "Success Create Cookie Supplier";
	}
	
	@GetMapping("/createCookieAdmin")
	@ResponseBody
	public String createCookieAdmin(HttpServletResponse response) {
		int cookieExpiry = 86400; 
		Cookie adminCookie = new Cookie("idAd", String.valueOf(1));
		adminCookie.setMaxAge(cookieExpiry);
		adminCookie.setPath("/");
		response.addCookie(adminCookie);
		return "Success Create Cookie Admin";
	}
	
	@GetMapping("/clearAllCookie")
	@ResponseBody
	public String deleteAllCookie(HttpServletResponse response) {
		Cookie adminCookie = new Cookie("idAd", String.valueOf(1));
		Cookie supplierCookie = new Cookie("idSupp", String.valueOf(1));
		Cookie userCookie = new  Cookie("idUsers", String.valueOf(1));
		supplierCookie.setMaxAge(0);
		supplierCookie.setPath("/");
		adminCookie.setMaxAge(0);
		adminCookie.setPath("/");
		userCookie.setMaxAge(0);
		userCookie.setPath("/");
		response.addCookie(adminCookie);
		response.addCookie(supplierCookie);
		response.addCookie(userCookie);
		return "Success Clear All Cookie";
	}
	
}
