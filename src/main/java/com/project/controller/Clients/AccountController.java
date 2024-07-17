package com.project.controller.Clients;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.repository.ilaProjectCoffeeRepository;
import com.project.utils.StringValue;
import com.project.model.*;
@Controller
@RequestMapping("/Account")
public class AccountController {
	
	@GetMapping("/ajax/getUserAddress")
	public String userGetAllAddress(@CookieValue(value = StringValue.nameCookieUser) int idUsers ,Model model) {
		model.addAttribute("lsAddress",ilaProjectCoffeeRepository.getInstance().userGetAllAddress(idUsers));
		return "/AjaxView/UserAddress";
	}
	
	@GetMapping("/ajax/viewDetailsAddress")
	public String viewDetailsAddress(int idAddress,Model model) {
		model.addAttribute("userAddress", ilaProjectCoffeeRepository.getInstance().userGetDetailsAddress(idAddress));
		return "/AjaxView/UserEditAddress";
	}
	
	@GetMapping("/ajax/viewNewAddress")
	public String viewNewAddress() {
		return "/AjaxView/UserAddAddress";
	}
	
	@GetMapping("/ajax/UpdateAddress")
	public String updateAddress(@CookieValue(value = StringValue.nameCookieUser) int idUsers ,int idAddress,String Address,Boolean ckDefault) {
		Address modelUpdate = new Address();
		modelUpdate.setId(idAddress);
		modelUpdate.setUserAddress(Address);
		modelUpdate.setStatus(ckDefault);
		modelUpdate.setIdAccount(idUsers);
		//
		ilaProjectCoffeeRepository.getInstance().userUpdateAddress(modelUpdate);
		return "redirect:/Account/ajax/getUserAddress";
	}
	
	@GetMapping("/ajax/InsertAddress")
	public String insertAddress(@CookieValue(value = StringValue.nameCookieUser) int idUsers ,String Address,Boolean ckDefault) {
		Address modelInsert = new Address();
		modelInsert.setIdAccount(idUsers);
		modelInsert.setUserAddress(Address);
		modelInsert.setStatus(ckDefault);
		//
		ilaProjectCoffeeRepository.getInstance().userInsertAddress(modelInsert);
		return "redirect:/Account/ajax/getUserAddress";
	}
	
	@GetMapping("/ajax/DeleteAddress")
	@ResponseBody
	public void deleteAddress(int idAddress) {
		ilaProjectCoffeeRepository.getInstance().userDeleteAddress(idAddress);
	}
		
	
	
	
	
	
	
	
	
}
