package com.project.controller.Admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.model.Voucher;
import com.project.modelview.InvoiceView;
import com.project.repository.ilaProjectCoffeeRepository;

@Controller
@RequestMapping("/Admin")
public class DashboardController {
	@GetMapping("/dashboard")
	public String dashboard(Model lstHouse) {
		ilaProjectCoffeeRepository.getInstance().AD_autoCheckSupplierNotConfirmA();
		ilaProjectCoffeeRepository.getInstance().AD_autoCheckConfirmA();
		lstHouse.addAttribute("countInvCurrDate", ilaProjectCoffeeRepository.getInstance().AD_countInvCurrDate());
		lstHouse.addAttribute("profitsCurrDate", ilaProjectCoffeeRepository.getInstance().AD_profitsCurrDate());

		lstHouse.addAttribute("lstViewMain", viewMain());
		return "Admin/dashboard";
	}
	
	private List<InvoiceView> viewMain() {
		List<InvoiceView> lstMain = new ArrayList<>();
		try {
			List<InvoiceView> getCus = ilaProjectCoffeeRepository.getInstance().CallGetName_Phone_Date_TotalInvoice();
			for (int i = 0; i < getCus.size(); i++) {
				InvoiceView itemMain = new InvoiceView();
				itemMain.setId(getCus.get(i).getId());
				itemMain.setIdAccount(getCus.get(i).getIdAccount());
				itemMain.setNameCus(getCus.get(i).getNameCus());
				itemMain.setPhone(getCus.get(i).getPhone());
				itemMain.setCreateDate(getCus.get(i).getCreateDate());
				itemMain.setTotalPrice(getCus.get(i).getTotalPrice());
				itemMain.setIsStatus(getCus.get(i).getIsStatus());
				lstMain.add(itemMain);
				if(i>=5) {
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return lstMain;
	}

}
