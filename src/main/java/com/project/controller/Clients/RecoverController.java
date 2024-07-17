package com.project.controller.Clients;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.model.RecoverInfo;
import com.project.model.SendingMail;
import com.project.repository.ilaProjectCoffeeRepository;
import com.project.utils.StringValue;

@Controller
@RequestMapping("/Recover")
public class RecoverController {
	
	@GetMapping("/Password")
	public String userRecoverPassword() {
		return "/Users/RecoverPassword";
	}
	
	@GetMapping("/sendMailRecoverPassword")
	@ResponseBody
	public RecoverInfo userRecoverPassword(String email) {
		int userType = 2 ;
		RecoverInfo rEI =  ilaProjectCoffeeRepository.getInstance().userRecoverPassword(email, userType);
		if (rEI.isFlagSuccess() && rEI.isFlagSendding()) {
			SendingMail sd = new SendingMail();
			sd.setMailSubject("Your Password Reset Link");
			sd.setSentTo(email);
			ilaProjectCoffeeRepository.getInstance().sendMailForgotPassword(sd, rEI);
		}
		return rEI;
	}

	@GetMapping("/VerifyRecoverPass")
	public String userVerifyRecoverPass(@CookieValue(value = StringValue.nameCookieUser, defaultValue = "0") int idUsers, String codeRecover, Model model) {
		boolean flagCheck = ilaProjectCoffeeRepository.getInstance().userCheckRecoverkeyPassword(codeRecover);
		if (!flagCheck) {
			/**
			 * @Check Redirect Page
			 */
			if (idUsers == 0) {
				return "redirect:/UsersLogin";
			} else {
				return "redirect:/";
			}
		}
		model.addAttribute("idUser", idUsers);
		return "/Users/NewPassword";
	}
	
	@GetMapping("/ajax/changePasswordbyRecover")
	@ResponseBody
	public boolean userChangePasswordbyRecover(String newPassword,String keyRecover) {
		return ilaProjectCoffeeRepository.getInstance().userChangePasswordByRecover(newPassword, keyRecover);
	}
	
	
}
