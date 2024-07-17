package com.project.controller.Clients;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.project.model.HandleInvoiceObject;
import com.project.model.HandleStatusObject;
import com.project.model.StatusInvoice;
import com.project.model.menuUsers;
import com.project.modelview.InvoiceDetailsU;
import com.project.others.CheckCookieUsers;
import com.project.repository.ilaProjectCoffeeRepository;
import com.project.utils.StringValue;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/Invoice")
public class InvoiceController {

	@RequestMapping("/InvoicePayment")
	public RedirectView userInvoicePayment(@CookieValue(value = StringValue.nameCookieUser) int idUsers ,
			String lsCartSel, String idVoucher, String lsVoucherS, int idUserAddress,BigDecimal feeService ,RedirectAttributes redirectAttrs) {
		HandleInvoiceObject makeObject = new HandleInvoiceObject();
		makeObject.setIdAddress(idUserAddress);
		makeObject.setLsCartSelect(lsCartSel);
		makeObject.setIdUser(idUsers);
		makeObject.setLsVoucherS(lsVoucherS);
		makeObject.setVoucherAdmin(idVoucher);
		makeObject.setFeeService(feeService);
		var redirectView= new RedirectView("/Carts/",true);
		//
		boolean flagPayment = ilaProjectCoffeeRepository.getInstance().userPaymentInvoice(makeObject);
		redirectAttrs.addFlashAttribute("flagPayment", flagPayment);
		//
		return redirectView;
	}
	
	@GetMapping("/MyInvoice")
	public String userInvoice(@CookieValue(value = StringValue.nameCookieUser) int idUsers , HttpServletRequest request, Model model) {		
		model.addAttribute("lsInvoice",ilaProjectCoffeeRepository.getInstance().userGetAllInvoice(idUsers));
		menuUsers mU = new menuUsers();
		mU.setInvoicePage(true);
		mU.setFlagLogin(CheckCookieUsers.getInstance().checkCookieExists(request, StringValue.nameCookieUser));
		model.addAttribute("menuUsers", mU);
		//
		return "Users/UsersInvoice";
	}
	
	@GetMapping("/ajax/SearchInvoiceCode")
	public String userSearchInvoice(@CookieValue(value = StringValue.nameCookieUser) int idUsers ,String codeSearch,Model model) {
		model.addAttribute("lsInvoice", ilaProjectCoffeeRepository.getInstance().userSearchInvoice(idUsers,codeSearch));
		return "AjaxView/UserSearchInvoice";
	}
	
	@GetMapping("/ajax/LoadNormalData")
	public String userLoadInvoiceNormal(@CookieValue(value = StringValue.nameCookieUser) int idUsers ,Model model) {
		model.addAttribute("lsInvoice", ilaProjectCoffeeRepository.getInstance().userGetAllInvoice(idUsers));
		return "AjaxView/UserSearchInvoice";
	}
	
	@GetMapping("/InvoiceDetails")
	public String userInvoiceDetails(@CookieValue(value = StringValue.nameCookieUser) int idUsers , int idInvoice,Model model, HttpServletRequest request) {
		ilaProjectCoffeeRepository.getInstance().autoConfirmSupplierInvoiceU(idInvoice);
		ilaProjectCoffeeRepository.getInstance().autoCheckNotConfirmSupplierU(idInvoice);
		List<InvoiceDetailsU> ls = ilaProjectCoffeeRepository.getInstance().userGetInvoiceDetails(idUsers, idInvoice);
		if (ls.size()==0) {
			return "redirect:/Invoice/MyInvoice";
		}
		model.addAttribute("lsSuppliers",ilaProjectCoffeeRepository.getInstance().userGetSupplierInvDetails(idInvoice));
		model.addAttribute("userInfo",ilaProjectCoffeeRepository.getInstance().userGetInfoInvoice(idInvoice));
		model.addAttribute("lsInvoiceDetails",ls);
		model.addAttribute("feeService",ilaProjectCoffeeRepository.getInstance().userGetFeeService(idInvoice));
		//
		menuUsers mU = new menuUsers();
		mU.setInvoicePage(true);
		mU.setFlagLogin(CheckCookieUsers.getInstance().checkCookieExists(request, StringValue.nameCookieUser));
		model.addAttribute("menuUsers", mU);
		//
		return "Users/UsersInvoiceDetails";
	}
	
	@GetMapping("/ajax/ConfirmInvoiceDetails")
	@ResponseBody
	public Boolean userConfirmInvoiceDetails(@CookieValue(value = StringValue.nameCookieUser) int idUsers ,int idInvoice,int idInvoiceD,int idSupplier) {
		HandleStatusObject hsO = new HandleStatusObject();
		hsO.setIdUser(idUsers);
		hsO.setIdInvoice(idInvoice);
		hsO.setIdInvoiceDetails(idInvoiceD);
		hsO.setIdSupplier(idSupplier);
		hsO.setUserCase(2);
		hsO.setStatusType(0);		
		return ilaProjectCoffeeRepository.getInstance().userConfirmInvoiceDetails(hsO);
	}
}
