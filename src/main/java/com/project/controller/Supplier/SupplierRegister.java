package com.project.controller.Supplier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.model.Account;
import com.project.model.RecoverInfo;
import com.project.model.SendingMail;
import com.project.repository.ilaProjectCoffeeRepository;

@Controller
@RequestMapping("/Supplier/Register")
public class SupplierRegister {
	
	@GetMapping("")
	public String supplierRegister(Model model) {
		model.addAttribute("modeForm", "Register");
		return "/Supplier/SupplierRAndLogin";
	}
	
	@GetMapping("/checkExitsTitileSupplier")
	@ResponseBody
	public boolean checkExitsTitleofSupplier(String supplierTitle) {
		return ilaProjectCoffeeRepository.getInstance().checkExitsTitleOfSupplier(supplierTitle);
	}
	
	@GetMapping("/checkExistsUserNameSupplier")
	@ResponseBody
	public boolean checkExistsUserNameOfSupplier(String username) {
		return ilaProjectCoffeeRepository.getInstance().checkExistsUserNameOfSupplier(username);
	}
	
	@GetMapping("/checkExistsEmailOfSupplier")
	@ResponseBody
	public boolean checkExistsEmailOfSupplier(String supplierEmail) {
		return ilaProjectCoffeeRepository.getInstance().checkExistsEmailOfSupplier(supplierEmail);
	}
	
	@GetMapping("/supplierRegisterAccount")
	@ResponseBody
	public RecoverInfo userRegisterAccount(String supplierTitle,String userName,String userEmail, String userPassword) {
		/**
		 * @Make Model
		 */
		Account acRegister = new Account();
		acRegister.setName(supplierTitle);
		acRegister.setUsername(userName);
		acRegister.setEmail(userEmail);
		acRegister.setPassword(userPassword);
		//
		RecoverInfo rEI =  ilaProjectCoffeeRepository.getInstance().supplierRegisterAccount(acRegister);
		if (rEI.isFlagSuccess() && rEI.isFlagSendding()) {
			SendingMail sd = new SendingMail();
			sd.setMailSubject("Your Register Account Verification Link");
			sd.setSentTo(userEmail);
			ilaProjectCoffeeRepository.getInstance().supplierSendMailVerify(sd, rEI);
		}
		return rEI;
	}
	
	@GetMapping("/verifyNewAccount")
	public String userVerifyNewAccount(String codeLink,Model model) {
		model.addAttribute("flagVerify",ilaProjectCoffeeRepository.getInstance().supplierVerifyRegisterAccount(codeLink));
		return "/Supplier/SupplierAlertRegister";
	}
}


