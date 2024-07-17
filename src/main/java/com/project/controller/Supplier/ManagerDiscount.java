package com.project.controller.Supplier;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.model.Supplier;
import com.project.modelview.DiscountView;
import com.project.modelview.ProductView;
import com.project.others.Encode_Decode;
import com.project.repository.ilaProjectCoffeeRepository;
import com.project.utils.StringValue;

@RequestMapping("/Supplier")
@Controller
public class ManagerDiscount {
	@GetMapping("/ManagerDiscount")
	public String index(@CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier ,
			@CookieValue(value = "suppCookie", defaultValue = "0") String suppCookie, Model model) {
		ilaProjectCoffeeRepository.getInstance().autoStartDiscount(idSupplier);
		ilaProjectCoffeeRepository.getInstance().autoEndDiscount(idSupplier);
	
		String decodeSupp = Encode_Decode.getInstance().decodeString(suppCookie);
		model.addAttribute("suppCookie", Encode_Decode.getInstance().jsonToObject(decodeSupp, Supplier.class));

		model.addAttribute("idSupplier", idSupplier);
		model.addAttribute("AllDiscount", ilaProjectCoffeeRepository.getInstance().GetAllDiscount(idSupplier));
		model.addAttribute("AllProductNotHaveDiscount", ilaProjectCoffeeRepository.getInstance().GetAllProductNotHaveDiscount(idSupplier));
		return "Supplier/ManagerDiscount";
	}
	@GetMapping("/FilterDiscount")
	public @ResponseBody List<DiscountView> FilterDiscount(@CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier, String type) {
		if(type.equals("All")){
			return ilaProjectCoffeeRepository.getInstance().GetAllDiscount(idSupplier);			
		}else {
			return ilaProjectCoffeeRepository.getInstance().FilterDiscountWeb(idSupplier, Integer.parseInt(type));			
		}
	}
	
	@GetMapping("/AddDiscount")
	public String addDiscount(@ModelAttribute DiscountView d , String listId) {
		String[] str = listId.split(",");
		List<DiscountView> ls = new ArrayList<>();
		
		java.sql.Date now = new java.sql.Date(new java.util.Date().getTime());
		int check = now.compareTo(d.getDateBegin());
		if(check == 0) {
			d.setIsStatus(1);	
		}else{
			d.setIsStatus(0);	
		}

		for (String item : str) {
			DiscountView tempD = new DiscountView();
			tempD.setIdProduct(Integer.parseInt(item));
			tempD.setDateBegin(d.getDateBegin());
			tempD.setDateEnd(d.getDateEnd());
			tempD.setDiscount(d.getDiscount());
			ls.add(tempD);
		}
		
		boolean flag = ilaProjectCoffeeRepository.getInstance().InsertDiscount(ls);
		
		return "redirect:/Supplier/ManagerDiscount";
	}

	@GetMapping("/EditDiscount")
	public @ResponseBody List<DiscountView> editDiscount(@ModelAttribute DiscountView d, String indC, 
										String type, @CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier, String listIdProduct) {
		List<DiscountView> ls = new ArrayList<>();
		if (!listIdProduct.isEmpty()) {
			String[] str = listIdProduct.split(",");
			for (String item : str) {
				DiscountView tempD = new DiscountView();
				tempD.setIdProduct(Integer.parseInt(item));
				tempD.setDateBegin(d.getDateBegin());
				tempD.setDateEnd(d.getDateEnd());
				tempD.setDiscount(d.getDiscount());
				tempD.setIndC(Integer.parseInt(indC));
				ls.add(tempD);
			}
			boolean flag = ilaProjectCoffeeRepository.getInstance().InsertDiscount(ls);
		}

		d.setIndC(Integer.parseInt(indC));
		boolean flag = ilaProjectCoffeeRepository.getInstance().UpdateDiscount(d);

		if(type.equals("All")){
			return ilaProjectCoffeeRepository.getInstance().GetAllDiscount(idSupplier);			
		}else {
			return ilaProjectCoffeeRepository.getInstance().FilterDiscountWeb(idSupplier, Integer.parseInt(type));			
		}
	}

	@GetMapping("/DeleteDiscount")
	public @ResponseBody List<DiscountView> deleteDiscount(String indC, String type, @CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier) {
		ilaProjectCoffeeRepository.getInstance().DeleteDiscount(Integer.parseInt(indC));

		if(type.equals("All")){
			return ilaProjectCoffeeRepository.getInstance().GetAllDiscount(idSupplier);			
		}else {
			return ilaProjectCoffeeRepository.getInstance().FilterDiscountWeb(idSupplier, Integer.parseInt(type));			
		}
	}

	@GetMapping("/RemoveDisOfPro")
	public @ResponseBody List<DiscountView> removeDisOfPro(String indC, String idProducts, @CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier) {
		String[] str = idProducts.split(",");
		
		for (String idProduct : str) {
			ilaProjectCoffeeRepository.getInstance().RemoveDisOfPro(Integer.parseInt(idProduct), Integer.parseInt(indC));			
		}

		return ilaProjectCoffeeRepository.getInstance().DetailDiscount(Integer.parseInt(indC), idSupplier);
	}

	@GetMapping("/DetailDiscount")
	public @ResponseBody List<DiscountView> detailDiscount(String indC, @CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier) {
		return ilaProjectCoffeeRepository.getInstance().DetailDiscount(Integer.parseInt(indC), idSupplier);
	}

	@GetMapping("/AjaxProductNoDiscount")
	public @ResponseBody List<ProductView> ajaxProductNoDiscount(@CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier) {
		return ilaProjectCoffeeRepository.getInstance().GetAllProductNotHaveDiscount(idSupplier);
	}
}
