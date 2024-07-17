package com.project.controller.Supplier;

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
@RequestMapping("/Supplier/Recover")
public class SupplierRecover {
	@GetMapping("/Password")
	public String userRecoverPassword() {
		return "/Supplier/RecoverPassword";
	}
	
	@GetMapping("/sendMailRecoverPassword")
	@ResponseBody
	public RecoverInfo userRecoverPassword(String email) {
		int userType = 1 ;
		RecoverInfo rEI =  ilaProjectCoffeeRepository.getInstance().userRecoverPassword(email, userType);
		if (rEI.isFlagSuccess() && rEI.isFlagSendding()) {
			SendingMail sd = new SendingMail();
			sd.setMailSubject("Your Password Reset Link");
			sd.setSentTo(email);
			ilaProjectCoffeeRepository.getInstance().T_SendMailForgotPassword(sd, rEI);
		}
		return rEI;
	}

	@GetMapping("/VerifyRecoverPass")
	public String userVerifyRecoverPass(@CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier, String codeRecover, Model model) {
		boolean flagCheck = ilaProjectCoffeeRepository.getInstance().userCheckRecoverkeyPassword(codeRecover);
		if (!flagCheck) {
			/**
			 * @Check Redirect Page
			 */
			if (idSupplier == 0) {
				return "redirect:/Supplier/Login";
			} else {
				return "redirect:/Supplier/Dashboard";
			}
		}
		model.addAttribute("idSupplier", idSupplier);
		return "/Supplier/SupplierNewPassword";
	}
	
	@GetMapping("/ajax/changePasswordbyRecover")
	@ResponseBody
	public boolean userChangePasswordbyRecover(String newPassword,String keyRecover) {
		return ilaProjectCoffeeRepository.getInstance().userChangePasswordByRecover(newPassword, keyRecover);
	}
}
