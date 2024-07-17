package com.project.controller.Clients;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.model.ObjectMessage;
import com.project.model.RecoverInfo;
import com.project.model.SendingMail;
import com.project.repository.ilaProjectCoffeeRepository;
import com.project.utils.StringValue;

@Controller
@RequestMapping("/Email")
public class UserEmailController {
	
	@GetMapping("/ChangeEmail")
	public String userChangeEmail() {
		return "/Users/UserChangeEmail";
	}
	
	@GetMapping("/userCheckEmailChange")
	@ResponseBody
	public ObjectMessage userCheckEmailChange(@CookieValue(value = StringValue.nameCookieUser) int idUsers ,String newEmail) {
		return ilaProjectCoffeeRepository.getInstance().userCheckEmailForChange(idUsers, newEmail);
	}
	
	@GetMapping("/userRequestSendMail")
	@ResponseBody
	public RecoverInfo userRequestSendNewMail(@CookieValue(value = StringValue.nameCookieUser) int idUsers, String newEmail) {
		RecoverInfo rEI =  ilaProjectCoffeeRepository.getInstance().userRequestSendNewMail(idUsers, 2, newEmail);
		if (rEI.isFlagSuccess() && rEI.isFlagSendding()) {
			SendingMail sd = new SendingMail();
			sd.setMailSubject("Your New Email Verification Link");
			sd.setSentTo(newEmail);
			ilaProjectCoffeeRepository.getInstance().sendChangeMailRequest(sd, rEI);
		}
		return rEI;
	}
	
	@GetMapping("/VerifyNewEmail")
	public String verifyNewEmail(String codeLink,Model model) {
		model.addAttribute("flagVerify",ilaProjectCoffeeRepository.getInstance().userVerifyChangeEmail(codeLink));
		return "/Users/UsersALertEmail";
	}
		
}
