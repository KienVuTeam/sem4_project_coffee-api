package com.project.controller.Supplier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.model.ObjectMessage;
import com.project.model.RecoverInfo;
import com.project.model.SendingMail;
import com.project.model.Supplier;
import com.project.others.Encode_Decode;
import com.project.repository.ilaProjectCoffeeRepository;
import com.project.utils.StringValue;

@RequestMapping("/Supplier")
@Controller
public class Profile {
	@GetMapping("/Profile")
	public String index(@CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier,
			@CookieValue(value = "suppCookie", defaultValue = "0") String suppCookie, Model model) {
		
		String decodeSupp = Encode_Decode.getInstance().decodeString(suppCookie);
		model.addAttribute("suppCookie", Encode_Decode.getInstance().jsonToObject(decodeSupp, Supplier.class));

		model.addAttribute("idSupplier", idSupplier);
		model.addAttribute("ProfileSupplier", ilaProjectCoffeeRepository.getInstance().GetProfileSupplier(idSupplier).get(0));
		return "Supplier/Profile";
	}
	
	@PostMapping("/UpdateProfile")
	public String updateProfile(@ModelAttribute Supplier modelUpdate, @CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier) {
		modelUpdate.setId(idSupplier);
		ilaProjectCoffeeRepository.getInstance().UpdateProfile2(modelUpdate);
		return "redirect:/Supplier/Profile";
	}
	
	@GetMapping("/ChangeAvatar")
	public @ResponseBody String changeAvatar(String avatar, int id) {
		return ilaProjectCoffeeRepository.getInstance().ChangeAvatarSupplier(avatar, id);
	}
	
	@GetMapping("/CheckPassword")
	public @ResponseBody boolean checkPassword(@CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier, String password) {
		return ilaProjectCoffeeRepository.getInstance().CheckPassword(idSupplier, password);
	}
	
	@GetMapping("/ChangePassword")
	public String changePassword(String newPassword, String oldPassword, @CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier) {
		ilaProjectCoffeeRepository.getInstance().ChangePasswordSupplier(newPassword, oldPassword, idSupplier);
		return "redirect:/Supplier/Profile";
	}
	
	@GetMapping("/CheckDuplicateOrgName")
	public @ResponseBody boolean checkDuplicateOrgName(String title) {
		return ilaProjectCoffeeRepository.getInstance().CheckExistsOrganizationName(title);
	}
	
	@GetMapping("/CheckDuplicateEmail")
	public @ResponseBody boolean checkDuplicateEmail(String email) {
		return ilaProjectCoffeeRepository.getInstance().CheckExistsEmail(email);
	}
	
	@GetMapping("/CheckDuplicateUsername")
	public @ResponseBody boolean checkDuplicateUsername(String username) {
		return ilaProjectCoffeeRepository.getInstance().CheckExistsUsername(username);
	}
	
	@GetMapping("/CheckDuplicatePhone")
	public @ResponseBody boolean checkDuplicatePhone(String phone) {
		return ilaProjectCoffeeRepository.getInstance().CheckExistsPhone(phone);
	}
	
	// ============ Supplier Change Mail ==============
	@GetMapping("/suppCheckEmailChange")
	public @ResponseBody ObjectMessage userCheckEmailChange(int idSupplier ,String newEmail) {
		return ilaProjectCoffeeRepository.getInstance().suppCheckEmailForChange(idSupplier, newEmail);
	}
	
	@GetMapping("/requestChangeEmail")
	@ResponseBody
	public RecoverInfo requestChangeEmail(@CookieValue(value = StringValue.nameCookieSupplier) int idSupplier, String newEmail) {
		RecoverInfo rEI =  ilaProjectCoffeeRepository.getInstance().userRequestSendNewMail(idSupplier, 2, newEmail);
		if (rEI.isFlagSuccess() && rEI.isFlagSendding()) {
			SendingMail sd = new SendingMail();
			sd.setMailSubject("Your New Email Verification Link");
			sd.setSentTo(newEmail);
			ilaProjectCoffeeRepository.getInstance().T_SendChangeMailRequest(sd, rEI);
		}
		return rEI;
	}
	
	@GetMapping("/VerifyNewEmail")
	public String verifyNewEmail(String codeLink,@CookieValue(value = StringValue.nameCookieSupplier) int idSupplier,Model model) {
		model.addAttribute("flagVerify",ilaProjectCoffeeRepository.getInstance().SuppVerifyChangeEmail(codeLink));

		model.addAttribute("idSupplier", idSupplier);
		model.addAttribute("ProfileSupplier", ilaProjectCoffeeRepository.getInstance().GetProfileSupplier(idSupplier).get(0));
		return "Supplier/Profile";
	}
}