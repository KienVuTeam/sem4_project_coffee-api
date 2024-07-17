package com.project.controller.Supplier;


import java.time.YearMonth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.model.Supplier;
import com.project.others.Encode_Decode;
import com.project.repository.ilaProjectCoffeeRepository;

@Controller
public class Dashboard {
	@GetMapping("/Supplier/Dashboard")
	public String index(@CookieValue(value = "suppCookie", defaultValue = "0") String suppCookie, Model model) {
		String decodeSupp = Encode_Decode.getInstance().decodeString(suppCookie);
		Supplier supplier = (Supplier) Encode_Decode.getInstance().jsonToObject(decodeSupp, Supplier.class);

		model.addAttribute("suppCookie", supplier);
		
		// ======= Variable Revenue ========
		int year = YearMonth.now().getYear();
		int month = YearMonth.now().getMonthValue();
		model.addAttribute("RevenueToday", ilaProjectCoffeeRepository.getInstance().Sup_CallRevenueToday(supplier.getId()));
		model.addAttribute("RevenuePreMonth", ilaProjectCoffeeRepository.getInstance().Sup_CallRevenuePreMonth(supplier.getId()));
		model.addAttribute("CountOrder", ilaProjectCoffeeRepository.getInstance().Sup_CallCountOrder(supplier.getId()));
		model.addAttribute("TotalRevenueYear", ilaProjectCoffeeRepository.getInstance().Sup_CallTotalRevenueYear(supplier.getId()));
		model.addAttribute("RevenueDefault", ilaProjectCoffeeRepository.getInstance().Sup_CallRevenue(supplier.getId(), year, month));
		
		model.addAttribute("AllProduct", ilaProjectCoffeeRepository.getInstance().GetProductSupplier(supplier.getId()));
		model.addAttribute("Categories", ilaProjectCoffeeRepository.getInstance().GetCategory());
		return "Supplier/Dashboard";
	}
}
