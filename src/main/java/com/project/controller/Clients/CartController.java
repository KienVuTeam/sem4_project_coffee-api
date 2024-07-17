package com.project.controller.Clients;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.model.ObjectMessage;
import com.project.model.Voucher;
import com.project.model.menuUsers;
import com.project.modelview.CartView;
import com.project.modelview.VoucherView;
import com.project.others.CheckCookieUsers;
import com.project.repository.ilaProjectCoffeeRepository;
import com.project.utils.StringValue;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/Carts")
public class CartController {
	
	@GetMapping("/ajaxAddProductIntoCart")
	@ResponseBody
	public ObjectMessage ajaxAddProductIntoCart(@CookieValue(value = StringValue.nameCookieUser) int idUsers ,int idProduct,int Amount) {
		CartView makeModel = new CartView();
		makeModel.setIdProduct(idProduct);
		makeModel.setAddAmount(Amount);
		makeModel.setIdAccount(idUsers);
		//
		ObjectMessage msgO = new ObjectMessage();
		msgO.setFlagMessage(false);
		msgO.setMessage("Success Add Product Into Carts");
		return ilaProjectCoffeeRepository.getInstance().userAddProductIntoCart(makeModel);
	}
	
	@GetMapping("/")
	public String UserCart(@CookieValue(value = StringValue.nameCookieUser) int idUsers , HttpServletRequest request, Model model) {
		ilaProjectCoffeeRepository.getInstance().autoStartEndDiscountUsers();
		ilaProjectCoffeeRepository.getInstance().autoUpdateItemInCart(idUsers);
		//
		model.addAttribute("lsSupplierCart",ilaProjectCoffeeRepository.getInstance().getUserCartSupplier(idUsers));
		model.addAttribute("lsCart",ilaProjectCoffeeRepository.getInstance().getUserCart(idUsers));
		//
		menuUsers mU = new menuUsers();
		mU.setCartPage(true);
		mU.setFlagLogin(CheckCookieUsers.getInstance().checkCookieExists(request, StringValue.nameCookieUser));
		model.addAttribute("menuUsers", mU);
		return "/Users/UsersCart";
	}
	
	@GetMapping("/ajax/updateCart")
	@ResponseBody
	public BigDecimal UserUpdateCart(@CookieValue(value = StringValue.nameCookieUser) int idUsers ,int Amount, BigDecimal Price,int idProduct) {
		return ilaProjectCoffeeRepository.getInstance().updateUserCart(Amount, Price, idUsers, idProduct);
	}
	
	@GetMapping("/ajax/removeCart/")
	@ResponseBody
	public boolean RemoveCart(int idCart) {
		return ilaProjectCoffeeRepository.getInstance().removeCart(idCart);
	}
	
	@GetMapping("/ajax/multiRemoveCart/")
	@ResponseBody
	public boolean MultiRemoveCart(String lsIdCart) {
		return ilaProjectCoffeeRepository.getInstance().multiRemoveCart(lsIdCart);
	}
	
	@GetMapping("/CheckOut")
	public String CartCheckOut(@CookieValue(value = StringValue.nameCookieUser) int idUsers ,String lsCartSel,
			HttpServletRequest request ,String idVoucher,Model model) {
		boolean flagCheckOut = ilaProjectCoffeeRepository.getInstance().checkCartCheckOut(lsCartSel, idUsers,idVoucher);
		if (!flagCheckOut) {
			return "redirect:/Carts/";
		}
		model.addAttribute("userAddress",ilaProjectCoffeeRepository.getInstance().getUserAddress(idUsers));
		model.addAttribute("lsSuppliers",ilaProjectCoffeeRepository.getInstance().getListSupplierCheckOut(lsCartSel));
		model.addAttribute("lsCart",ilaProjectCoffeeRepository.getInstance().getListCartCheckOut(lsCartSel));
		if(!idVoucher.equals("BLANK")) {
			model.addAttribute("voucherAdmin",ilaProjectCoffeeRepository.getInstance().userGetVoucherDetails(idVoucher));
		}
		//
		menuUsers mU = new menuUsers();
		mU.setCartPage(true);
		mU.setFlagLogin(CheckCookieUsers.getInstance().checkCookieExists(request, StringValue.nameCookieUser));
		model.addAttribute("menuUsers", mU);
		//
		return "/Users/UsersCheckOut";
		
	}
	
	@GetMapping("/ajax/getVoucherSupplier")
	public String getVoucherSupplier(int idSupplier,Model model) {
		ilaProjectCoffeeRepository.getInstance().autoStartVoucherType(String.valueOf(idSupplier));
		ilaProjectCoffeeRepository.getInstance().autoEndVoucherType(String.valueOf(idSupplier));
		//
		List<VoucherView> lsVoucher = ilaProjectCoffeeRepository.getInstance().userGetVoucherSupplierbyID(idSupplier);
		if (lsVoucher.isEmpty()) {
			model.addAttribute("msg", "false");
			return "/Users/UsersMessageView";
		}
		model.addAttribute("lsVoucher", lsVoucher );
		return "/AjaxView/VoucherShopPopup";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
