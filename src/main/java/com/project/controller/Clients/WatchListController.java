package com.project.controller.Clients;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.model.ObjectMessage;
import com.project.model.menuUsers;
import com.project.others.CheckCookieUsers;
import com.project.repository.ilaProjectCoffeeRepository;
import com.project.utils.StringValue;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/WatchList")
public class WatchListController {
	
	@GetMapping("/")
	public String usersWatchList(@CookieValue(value = StringValue.nameCookieUser,defaultValue = "0") int idUsers,Model model, HttpServletRequest request) 
	{		
		model.addAttribute("idUsers",idUsers);
		//
		menuUsers mU = new menuUsers();
		mU.setWatchlistPage(true);
		mU.setFlagLogin(CheckCookieUsers.getInstance().checkCookieExists(request, StringValue.nameCookieUser));
		model.addAttribute("menuUsers", mU);
		//
		return "/Users/UsersWatchList";
	}
	
	@GetMapping("/loadUsersWatchList")
	public String usersLoadWList(int idUser,String lsWatchW,String searchProductN,
			int sortDFlag,int sortPFlag,Model model ) 
	{
		//
		model.addAttribute("lsW", ilaProjectCoffeeRepository.getInstance().usersGetWatchList
		(
			idUser,lsWatchW,searchProductN,	sortDFlag,sortPFlag
		));
		return "/AjaxView/UserWatchList";
	}
	
	@GetMapping("/userLoginAddWList")
	@ResponseBody
	public boolean userLoginAddWList(@CookieValue(value = StringValue.nameCookieUser ) int idUsers ,String lsProducts) {
		return ilaProjectCoffeeRepository.getInstance().userLoginAddWList(lsProducts, idUsers);
	}
	
	@GetMapping("/usersAddWatchList")
	@ResponseBody
	public ObjectMessage usersAddWatchList(@CookieValue(value = StringValue.nameCookieUser ) int idUsers, int idProduct) {
		return ilaProjectCoffeeRepository.getInstance().usersAddWatchList(idProduct, idUsers);
	}
	
	@GetMapping("/usersDeleteWatchList")
	@ResponseBody
	public boolean usersDeleteWatchList(@CookieValue(value = StringValue.nameCookieUser ) int idUsers ,int idProduct) {
		return ilaProjectCoffeeRepository.getInstance().usersDeleteWatchList(idProduct,idUsers);
	}
	
	
	
	
	
}
