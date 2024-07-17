package com.project.controller.Clients;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.model.Account;
import com.project.model.menuUsers;
import com.project.others.CheckCookieUsers;
import com.project.repository.ilaProjectCoffeeRepository;
import com.project.utils.StringValue;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/Profile")
public class ProfileController {
	
	@GetMapping("/")
	public String userProfile(@CookieValue(value = StringValue.nameCookieUser) int idUsers ,Model model, HttpServletRequest request) {
		model.addAttribute("account",ilaProjectCoffeeRepository.getInstance().userGetProfiles(idUsers));
		menuUsers mU = new menuUsers();
		mU.setHomePage(true);
		mU.setFlagLogin(CheckCookieUsers.getInstance().checkCookieExists(request, StringValue.nameCookieUser));
		model.addAttribute("menuUsers", mU);
		//
		return "/Users/UsersProfile";
	}
	
	@GetMapping("/ajax/LoadEditProfile")
	public String loadEditProfile(@CookieValue(value = StringValue.nameCookieUser) int idUsers ,Model model) {
		model.addAttribute("account",ilaProjectCoffeeRepository.getInstance().userGetProfiles(idUsers));
		return "/AjaxView/UsersEditProfile";
	}
	
	@GetMapping("/ajax/LoadChangePass")
	public String loadChangePass() {
		return "/AjaxView/UsersChangePass";
	}
	
	@GetMapping("/ajax/UpdateProfile")
	@ResponseBody
	public boolean userUpdateProfile(@CookieValue(value = StringValue.nameCookieUser) int idUsers ,String userAvatar,String userFullName,String userPhone) {
		Account modelUpdate = new Account();
		modelUpdate.setId(idUsers);
		modelUpdate.setAvatar(userAvatar);
		modelUpdate.setName(userFullName);
		modelUpdate.setPhone(userPhone);
		return ilaProjectCoffeeRepository.getInstance().userUpdateProfile(modelUpdate);
	}
	
	@GetMapping("/ajax/UsersVerifyPassword")
	@ResponseBody
	public boolean usersVerifyPassword(@CookieValue(value = StringValue.nameCookieUser) int idUsers ,String currentPassword) {
		return ilaProjectCoffeeRepository.getInstance().usersVerifyOldPass(idUsers, currentPassword);
	}
	
	@GetMapping("/ajax/UsersChangePassword")
	@ResponseBody
	public boolean usersChangePassword(@CookieValue(value = StringValue.nameCookieUser) int idUsers ,String oldPassword,String newPassword) {
		return ilaProjectCoffeeRepository.getInstance().usersChangePassword(idUsers, oldPassword, newPassword);
	}
}
