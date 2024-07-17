package com.project.controller.Clients;

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
@RequestMapping("/Register")
public class RegisterController {
	
	@GetMapping("/checkExistsUserName")
	@ResponseBody
	public boolean userCheckExistsUserName(String userName) {
		return ilaProjectCoffeeRepository.getInstance().userCheckExistsUserName(userName);
	}
	
	@GetMapping("/checkExistsUserEmail")
	@ResponseBody
	public boolean userCheckExistsUserEmail(String userEmail) {
		return ilaProjectCoffeeRepository.getInstance().userCheckExistsUserEmail(userEmail);
	}
	
	@GetMapping("/userRegisterAccount")
	@ResponseBody
	public RecoverInfo userRegisterAccount(String userFName,String userName,String userEmail,String userPhone,
			String userAddress, String userPassword) {
		/**
		 * @Make Model
		 */
		Account acRegister = new Account();
		acRegister.setName(userFName);
		acRegister.setUsername(userName);
		acRegister.setEmail(userEmail);
		acRegister.setPhone(userPhone);
		acRegister.setAddress(userAddress);
		acRegister.setPassword(userPassword);
		//
		RecoverInfo rEI =  ilaProjectCoffeeRepository.getInstance().userRegisterAccount(acRegister);
		if (rEI.isFlagSuccess() && rEI.isFlagSendding()) {
			SendingMail sd = new SendingMail();
			sd.setMailSubject("Your Register Account Verification Link");
			sd.setSentTo(userEmail);
			ilaProjectCoffeeRepository.getInstance().sendMailRegisterVerification(sd, rEI);
		}
		return rEI;
	}
	
	@GetMapping("/verifyNewAccount")
	public String userVerifyNewAccount(String codeLink,Model model) {
		model.addAttribute("flagVerify",ilaProjectCoffeeRepository.getInstance().userVerifyRegisterAccount(codeLink));
		return "/Users/UsersAlertRegister";
	}
}
