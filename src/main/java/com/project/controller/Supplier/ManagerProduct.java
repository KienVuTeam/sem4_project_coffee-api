package com.project.controller.Supplier;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.modelview.ProductView;
import com.project.others.Encode_Decode;
import com.project.model.Product;
import com.project.model.ReviewViewS;
import com.project.model.Supplier;
import com.project.repository.ilaProjectCoffeeRepository;
import com.project.utils.StringValue;


@RequestMapping("/Supplier")
@Controller
public class ManagerProduct {
	@GetMapping("/ManagerProduct")
	public String index(@CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier,
			@CookieValue(value = "statusSuppCookie", defaultValue = "0") int statusSupp, 
			@CookieValue(value = "nameSuppCookie", defaultValue = "0") String nameSuppCookie, 
			@CookieValue(value = "suppCookie", defaultValue = "0") String suppCookie,Model model) {
		ilaProjectCoffeeRepository.getInstance().autoStartDiscount(idSupplier);
		ilaProjectCoffeeRepository.getInstance().autoEndDiscount(idSupplier);
		
		String decodeSupp = Encode_Decode.getInstance().decodeString(suppCookie);
		model.addAttribute("suppCookie", Encode_Decode.getInstance().jsonToObject(decodeSupp, Supplier.class));
		
		model.addAttribute("idSupplier", idSupplier);
		model.addAttribute("statusSupp", statusSupp);
		model.addAttribute("nameSuppCookie", Encode_Decode.getInstance().decodeString(nameSuppCookie));
		model.addAttribute("AllProduct", ilaProjectCoffeeRepository.getInstance().GetProductSupplier(idSupplier));
		model.addAttribute("Categories", ilaProjectCoffeeRepository.getInstance().GetCategory());
		return "Supplier/ManagerProduct";
	}
	
	@GetMapping("/FilterProduct")
	public @ResponseBody List<ProductView> filterProduct(Model model, @CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier, String isActive) {
		if(isActive.equals("All")) {
			return ilaProjectCoffeeRepository.getInstance().GetProductSupplier(idSupplier);
		}
		return ilaProjectCoffeeRepository.getInstance().FilterActive(idSupplier, Integer.parseInt(isActive));
	}
	
	@GetMapping("/AddProduct")
	public String addProduct(Model model, @ModelAttribute ProductView pv, String idCate){
		
		pv.setIdcate(Integer.parseInt(idCate));
		pv.setIsActive(0);
		
		ilaProjectCoffeeRepository.getInstance().InsertProduct(pv);
		return "redirect:/Supplier/ManagerProduct"; 
	}
	
	@GetMapping("/EditProduct")
	public String editProduct(@ModelAttribute Product pv, String idProduct){
		pv.setId(Integer.parseInt(idProduct));
		ilaProjectCoffeeRepository.getInstance().UpdateProduct(pv);
		return "redirect:/Supplier/ManagerProduct"; 
	}
	
	@PostMapping("/DetailReview")
	public @ResponseBody List<ReviewViewS> detailReview(@RequestBody String param) {
		List<ReviewViewS> ls = ilaProjectCoffeeRepository.getInstance().GetDetailReview(Integer.parseInt(param));
		return ls;
	}
	
	@GetMapping("/DeleteProduct")
	public @ResponseBody List<ProductView> deleteProduct(String id, @CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier, String isActive) {
		// Delete 
		ilaProjectCoffeeRepository.getInstance().DeleteProduct(Integer.parseInt(id));
		
		// Return Ajax
		if(isActive.equals("All")) {
			return ilaProjectCoffeeRepository.getInstance().GetProductSupplier(idSupplier);
		}
		return ilaProjectCoffeeRepository.getInstance().FilterActive(idSupplier, Integer.parseInt(isActive));
	}
	
	@GetMapping("/ActiveProduct")
	public String activeProduct(int id) {
		ilaProjectCoffeeRepository.getInstance().ActiveProduct(id);
		return "redirect:/Supplier/ManagerProduct";
	}
	
	@GetMapping("/CheckDuplicateTitle")
	public @ResponseBody boolean checkDuplicateTitle(String title, @CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier) {
		return ilaProjectCoffeeRepository.getInstance().GetSearchNameProduct(title, idSupplier);
	}
}
