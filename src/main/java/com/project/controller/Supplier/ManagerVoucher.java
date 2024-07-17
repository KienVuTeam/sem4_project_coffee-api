package com.project.controller.Supplier;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.modelview.DiscountView;
import com.project.others.Encode_Decode;
import com.project.calling.DiscountCalling;
import com.project.calling.ProductsCalling;
import com.project.calling.VoucherCalling;
import com.project.model.Discount;
import com.project.model.Supplier;
import com.project.model.Voucher;
import com.project.repository.ilaProjectCoffeeRepository;
import com.project.utils.StringValue;

@RequestMapping("/Supplier")
@Controller
public class ManagerVoucher {
	@GetMapping("/ManagerVoucher")
	public String index(@CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier,
			@CookieValue(value = "suppCookie", defaultValue = "0") String suppCookie,Model model) {
		ilaProjectCoffeeRepository.getInstance().autoStartVoucherType(String.valueOf(idSupplier));
		ilaProjectCoffeeRepository.getInstance().autoEndVoucherType(String.valueOf(idSupplier));

		String decodeSupp = Encode_Decode.getInstance().decodeString(suppCookie);
		model.addAttribute("suppCookie", Encode_Decode.getInstance().jsonToObject(decodeSupp, Supplier.class));

		model.addAttribute("idSupplier", idSupplier);
		model.addAttribute("AllVoucher", ilaProjectCoffeeRepository.getInstance().GetAllVoucher(String.valueOf(idSupplier)));
		return "Supplier/ManagerVoucher";
	}
	
	@PostMapping("/AddVoucher")
	public String addVoucher(@CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier,Model model, @ModelAttribute Voucher v) {	
		v.setUsercreate(String.valueOf(idSupplier));
		v.setId(v.getId().toUpperCase());
		
		java.sql.Date now = new java.sql.Date(new java.util.Date().getTime());
		int check = now.compareTo(v.getStartDate());
		if(check == 0) {
			v.setIsActive(1);			
		}else{
			v.setIsActive(0);	
		}
		
		boolean flag = ilaProjectCoffeeRepository.getInstance().InsertVoucher(v);
		return "redirect:/Supplier/ManagerVoucher";
	}
	
	@GetMapping("/EditVoucher")
	public @ResponseBody List<Voucher> editVoucher(@ModelAttribute Voucher voucher, String id, @CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier, String type) {
		boolean flag = ilaProjectCoffeeRepository.getInstance().UpdateVoucher(voucher);

		switch (type) {
			case "All": return ilaProjectCoffeeRepository.getInstance().GetAllVoucher(String.valueOf(idSupplier));
			case "1": return ilaProjectCoffeeRepository.getInstance().FilterVoucher2(String.valueOf(idSupplier));
			case "2": return ilaProjectCoffeeRepository.getInstance().FilterVoucher1(String.valueOf(idSupplier));
			case "3": return ilaProjectCoffeeRepository.getInstance().FilterVoucher3(String.valueOf(idSupplier));
			default: return new ArrayList<Voucher>();
		}
	}
	
	@GetMapping("/DeleteVoucher")
	public @ResponseBody List<Voucher> deleteVoucher(String idVoucher, String type, @CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier) {
		ilaProjectCoffeeRepository.getInstance().DeleteVoucher(idVoucher);
		
		switch (type) {
			case "All": return ilaProjectCoffeeRepository.getInstance().GetAllVoucher(String.valueOf(idSupplier));
			case "1": return ilaProjectCoffeeRepository.getInstance().FilterVoucher2(String.valueOf(idSupplier));
			case "2": return ilaProjectCoffeeRepository.getInstance().FilterVoucher1(String.valueOf(idSupplier));
			case "3": return ilaProjectCoffeeRepository.getInstance().FilterVoucher3(String.valueOf(idSupplier));
			default: return new ArrayList<Voucher>();
		}
	}
	
	@GetMapping("/EndVoucher")
	public @ResponseBody List<Voucher> endVoucher(String idVoucher, String type, @CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier) {
		ilaProjectCoffeeRepository.getInstance().EndVoucher(idVoucher);
		
		switch (type) {
			case "All": return ilaProjectCoffeeRepository.getInstance().GetAllVoucher(String.valueOf(idSupplier));
			case "1": return ilaProjectCoffeeRepository.getInstance().FilterVoucher2(String.valueOf(idSupplier));
			case "2": return ilaProjectCoffeeRepository.getInstance().FilterVoucher1(String.valueOf(idSupplier));
			case "3": return ilaProjectCoffeeRepository.getInstance().FilterVoucher3(String.valueOf(idSupplier));
			default: return new ArrayList<Voucher>();
		}
	}
	
	@GetMapping("/FilterVoucher")
	public @ResponseBody List<Voucher> FilterVoucher(@CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier, String type) {
		switch (type) {
			case "All": return ilaProjectCoffeeRepository.getInstance().GetAllVoucher(String.valueOf(idSupplier));
			case "1": return ilaProjectCoffeeRepository.getInstance().FilterVoucher2(String.valueOf(idSupplier));
			case "2": return ilaProjectCoffeeRepository.getInstance().FilterVoucher1(String.valueOf(idSupplier));
			case "3": return ilaProjectCoffeeRepository.getInstance().FilterVoucher3(String.valueOf(idSupplier));
			default: return new ArrayList<Voucher>();
		}
	}
	
	@GetMapping("/CheckDuplicateIDVoucher")
	public @ResponseBody boolean CheckDuplicateIDVoucher(Model model, String id) {	
		boolean flag = ilaProjectCoffeeRepository.getInstance().CheckDuplicateID(id);
		return flag;
	}

}
