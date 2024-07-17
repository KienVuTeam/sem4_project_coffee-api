
package com.project.controller.Clients;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.project.model.Voucher;
import com.project.model.menuUsers;
import com.project.modelview.ProductView;
import com.project.modelview.VoucherView;
import com.project.others.CheckCookieUsers;
import com.project.repository.ilaProjectCoffeeRepository;
import com.project.utils.StringValue;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/Products")
public class ProductController {
	
	@GetMapping("/")
	public String Index(@CookieValue(value = StringValue.nameCookieUser ,defaultValue = "0") int idUsers,Model model, HttpServletRequest request) {
		ilaProjectCoffeeRepository.getInstance().autoStartEndDiscountUsers();
		//
		model.addAttribute("lsProducts",ilaProjectCoffeeRepository.getInstance().userGetAllProduct());
		model.addAttribute("lsCate",ilaProjectCoffeeRepository.getInstance().userGetAllAvalibleCategory());
		model.addAttribute("idUsers",idUsers);
		//
		menuUsers mU = new menuUsers();
		mU.setProductsPage(true);
		mU.setFlagLogin(CheckCookieUsers.getInstance().checkCookieExists(request, StringValue.nameCookieUser));
		model.addAttribute("menuUsers", mU);
		//
		return "/Users/UsersProducts";
	}
	
	@GetMapping("/ajax/viewProductbyCate")
	public String ViewProductbyCate(@CookieValue(value = StringValue.nameCookieUser,defaultValue = "0") int idUsers,int idCate,Model model) {
		model.addAttribute("lsProducts",ilaProjectCoffeeRepository.getInstance().getProductforUserbyCate(idCate));
		model.addAttribute("idUsers",idUsers);
		return "/AjaxView/UserSubProduct";
	}
	
	@GetMapping("/ajax/viewProductbyPrice")
	public String ViewProductbyPrice(@CookieValue(value = StringValue.nameCookieUser ,defaultValue = "0") int idUsers ,BigDecimal minPrice,BigDecimal maxPrice,Model model) {
		List<ProductView> lsProducts = ilaProjectCoffeeRepository.getInstance().getProductforUserbyPrice(minPrice, maxPrice);
		if (lsProducts.isEmpty()) {
			model.addAttribute("msg", "false");
			return "/Users/UsersMessageView";
		}
		model.addAttribute("lsProducts", lsProducts);
		model.addAttribute("idUsers",idUsers);
		return "/AjaxView/UserSubProduct";
	}
	
	@GetMapping("/ajax/viewProductbyName")
	public String ViewProductbyName(@CookieValue(value = StringValue.nameCookieUser ,defaultValue = "0") int idUsers ,String textSearch,Model model) {
		model.addAttribute("lsProducts",ilaProjectCoffeeRepository.getInstance().getProductforUserbyName(textSearch));
		model.addAttribute("idUsers",idUsers);
		return "/AjaxView/UserSubProduct";
	}
	
	@GetMapping("/ajax/viewNormalData")
	public String viewNormalData(@CookieValue(value = StringValue.nameCookieUser ,defaultValue = "0") int idUsers,Model model) {
		model.addAttribute("lsProducts",ilaProjectCoffeeRepository.getInstance().userGetAllProduct());
		model.addAttribute("idUsers",idUsers);
		return"/AjaxView/UserSubProduct";
	}
	
	@GetMapping("/ajax/viewPopupProduct")
	public String viewPopupProduct(int idProduct,Model model) {
		model.addAttribute("Product",ilaProjectCoffeeRepository.getInstance().getProductforUserByID(idProduct));
		return "/AjaxView/UserSubPopup";
	}
	
	@GetMapping("/ViewProductDetails/")
	public String viewProductDetails(int idProduct,@CookieValue(value = StringValue.nameCookieUser ,defaultValue = "0") int idUsers,Model model, HttpServletRequest request) {
		ProductView pReturn = ilaProjectCoffeeRepository.getInstance().getProductforUserByID(idProduct);
		if (pReturn.getId() == 0) {
			return "redirect:/Products/";
		} else {
			model.addAttribute("Product",pReturn);
			model.addAttribute("idUsers",idUsers);
			//
			menuUsers mU = new menuUsers();
			mU.setProductsPage(true);
			mU.setFlagLogin(CheckCookieUsers.getInstance().checkCookieExists(request, StringValue.nameCookieUser));
			model.addAttribute("menuUsers", mU);
			//
			return "Users/UsersProductDetails";
		}
	}
	
	@GetMapping("/ajax/viewRandomProduct")
	public String viewRandomProduct(Model model,int currentID,int amountTake) {
		model.addAttribute("lsProducts",ilaProjectCoffeeRepository.getInstance().getProductRandomFilterbyID(currentID,amountTake));
		return "/AjaxView/UserSubRelatedProduct";
	}
	
	@GetMapping("/ajax/viewUserReviewProduct")
	public String viewUserReviewProduct(Model model,int idProduct) {
		model.addAttribute("lsReview",ilaProjectCoffeeRepository.getInstance().getUserReviewbyProductID(idProduct));
		return "/AjaxView/UserSubReview";
	}
	
	@GetMapping("/ProductsOfSupplier/")
	public String ViewProductofSupplier(@CookieValue(value = StringValue.nameCookieUser ,defaultValue = "0") int idUsers,
			Model model,int idSupplier , HttpServletRequest request) {
		ilaProjectCoffeeRepository.getInstance().autoStartDiscount(idSupplier);
		ilaProjectCoffeeRepository.getInstance().autoEndDiscount(idSupplier);
		//
		List<ProductView> lsProducts = ilaProjectCoffeeRepository.getInstance().userGetAllProductbySupplier(idSupplier);
		//
		if (lsProducts.size() == 0) {
			return "redirect:/Products/";
		}
		model.addAttribute("lsProducts",lsProducts);
		model.addAttribute("lsCate",ilaProjectCoffeeRepository.getInstance().userGetAvalibleCateSupp(idSupplier));
		model.addAttribute("supplierName",lsProducts.get(0).getSupplierName());
		model.addAttribute("idUsers",idUsers);
		//
		menuUsers mU = new menuUsers();
		mU.setProductsPage(true);
		mU.setFlagLogin(CheckCookieUsers.getInstance().checkCookieExists(request, StringValue.nameCookieUser));
		model.addAttribute("menuUsers", mU);
		//
		return "/Users/UsersProductsSupplier";
	}
	
	@GetMapping("/ajax/viewProductbyCateSupplier/")
	public String ViewProductbyCateSupplier(@CookieValue(value = StringValue.nameCookieUser ,defaultValue = "0") int idUsers,int idCate,int idSupplier,Model model) {
		model.addAttribute("lsProducts",ilaProjectCoffeeRepository.getInstance().getProductforUserbyCateSupplier(idCate,idSupplier));
		model.addAttribute("idUsers",idUsers);
		return "/AjaxView/UserSubProduct";
	}
	
	@GetMapping("/ajax/viewProductbyPriceSupplier/")
	public String ViewProductbyPriceSupplier(@CookieValue(value = StringValue.nameCookieUser ,defaultValue = "0") int idUsers,BigDecimal minPrice,BigDecimal maxPrice,int idSupplier,Model model) {
		List<ProductView> lsProducts = ilaProjectCoffeeRepository.getInstance().getProductforUserbyPriceSupplier(minPrice, maxPrice,idSupplier);
		if (lsProducts.isEmpty()) {
			model.addAttribute("msg", "false");
			return "/Users/UsersMessageView";
		}
		model.addAttribute("lsProducts", lsProducts);
		model.addAttribute("idUsers",idUsers);
		return "/AjaxView/UserSubProduct";
	}
	
	@GetMapping("/ajax/viewProductbyNameSupplier/")
	public String ViewProductbyNameSupplier(@CookieValue(value = StringValue.nameCookieUser ,defaultValue = "0") int idUsers,String textSearch,int idSupplier,Model model) {
		model.addAttribute("lsProducts",ilaProjectCoffeeRepository.getInstance().getProductforUserbyNameSupplier(textSearch,idSupplier));
		model.addAttribute("idUsers",idUsers);
		return "/AjaxView/UserSubProduct";
	}
	
	@GetMapping("/ajax/viewNormalDataSupplier/")
	public String viewNormalDataSupplier(@CookieValue(value =  StringValue.nameCookieUser ,defaultValue = "0") int idUsers,Model model,int idSupplier) {
		model.addAttribute("lsProducts",ilaProjectCoffeeRepository.getInstance().userGetAllProductbySupplier(idSupplier));
		model.addAttribute("idUsers",idUsers);
		return"/AjaxView/UserSubProduct";
	}
	
	@GetMapping("/ajax/getVoucherForUser/")
	public String getVoucherForUser(Model model,String voucherType) {
		ilaProjectCoffeeRepository.getInstance().autoStartVoucherType(voucherType);
		ilaProjectCoffeeRepository.getInstance().autoEndVoucherType(voucherType);
		//
		List<Voucher> lsVoucher = ilaProjectCoffeeRepository.getInstance().userGetAvalibleVoucher(voucherType);
		if (lsVoucher.isEmpty()) {
			model.addAttribute("msg", "false");
			return "/Users/UsersMessageView";
		}
		model.addAttribute("lsVouchers", lsVoucher);
		return "/AjaxView/VoucherView";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
