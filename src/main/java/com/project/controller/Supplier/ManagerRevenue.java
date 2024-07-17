package com.project.controller.Supplier;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.calling.RevenueCalling;
import com.project.model.InvoiceSupplier;
import com.project.model.Supplier;
import com.project.others.Encode_Decode;
import com.project.repository.ilaProjectCoffeeRepository;
import com.project.utils.StringValue;

@RequestMapping("/Supplier")
@Controller
public class ManagerRevenue {
	
	@GetMapping("/ManagerRevenue")
	public String index(@CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier, 
			@CookieValue(value = "suppCookie", defaultValue = "0") String suppCookie, Model model) {
		int year = YearMonth.now().getYear();
		int month = YearMonth.now().getMonthValue();
		
		String decodeSupp = Encode_Decode.getInstance().decodeString(suppCookie);
		model.addAttribute("suppCookie", Encode_Decode.getInstance().jsonToObject(decodeSupp, Supplier.class));
		
		model.addAttribute("idSupplier", idSupplier);
		model.addAttribute("RevenueToday", ilaProjectCoffeeRepository.getInstance().Sup_CallRevenueToday(idSupplier));
		model.addAttribute("RevenuePreMonth", ilaProjectCoffeeRepository.getInstance().Sup_CallRevenuePreMonth(idSupplier));
		model.addAttribute("CountOrder", ilaProjectCoffeeRepository.getInstance().Sup_CallCountOrder(idSupplier));
		model.addAttribute("TotalRevenueYear", ilaProjectCoffeeRepository.getInstance().Sup_CallTotalRevenueYear(idSupplier));
		model.addAttribute("RevenueDefault", ilaProjectCoffeeRepository.getInstance().Sup_CallRevenue(idSupplier, year, month));
		return "Supplier/ManagerRevenue";
	}
	
	@GetMapping("/SelectRevenue")
	public @ResponseBody List<InvoiceSupplier> selectRevenue(@CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier,
			Model model, String month, String year) {	
		return ilaProjectCoffeeRepository.getInstance().Sup_CallRevenue(idSupplier, Integer.parseInt(year), Integer.parseInt(month));
	}
}
