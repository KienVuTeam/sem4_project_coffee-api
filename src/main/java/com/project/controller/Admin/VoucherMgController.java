package com.project.controller.Admin;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.model.Voucher;
import com.project.repository.ilaProjectCoffeeRepository;

@Controller
@RequestMapping("/Admin")
public class VoucherMgController {
	@GetMapping("/voucher-management")
	public String dashboard(Model lstVou) {
		// auto
		ilaProjectCoffeeRepository.getInstance().autoStartVoucherType("0");
		ilaProjectCoffeeRepository.getInstance().autoEndVoucherType("0");
		lstVou.addAttribute("lstVou", ilaProjectCoffeeRepository.getInstance().CallAllVoucher());
		return "Admin/voucherMg";
	}

	@PostMapping("/insert-voucher")
	public String insertVoucher(@ModelAttribute Voucher addVoucher, Model lstVou) {
		long millis = System.currentTimeMillis();
		Date current = new Date(millis);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(addVoucher.getStartDate());
		String currentDate = formatter.format(current);
		if (current.before(addVoucher.getStartDate()) == true) {
			addVoucher.setIsActive(0);
		} else if (currentDate.equals(strDate)) {
			addVoucher.setIsActive(1);
		}
		addVoucher.setUsercreate("0");
		ilaProjectCoffeeRepository.getInstance().CallInsertVou(addVoucher);
		//
		lstVou.addAttribute("lstVou", ilaProjectCoffeeRepository.getInstance().CallAllVoucher());
		return "redirect:/Admin/voucher-management";
	}

	@GetMapping("/checkVoucher")
	@ResponseBody
	public boolean checkVoucher(String idVoucher) {
		Voucher vou = ilaProjectCoffeeRepository.getInstance().CallCheckVoucher(idVoucher);
		boolean check = false;
		if (vou.getId() == null) {
			check = false;
		} else {
			if (vou.getId().equals(idVoucher)) {
				check = true;
			}
		}

		return check;
	}

	@GetMapping("/filter1")
	public String filter1(Model lstVou) {
		ilaProjectCoffeeRepository.getInstance().autoStartVoucherType("0");
		ilaProjectCoffeeRepository.getInstance().autoEndVoucherType("0");
		lstVou.addAttribute("lstVou", ilaProjectCoffeeRepository.getInstance().CallFilter1());
		return "Admin/Share/partialVoucherMg";
	}

	@GetMapping("/filter2")
	public String filter2(Model lstVou) {
		ilaProjectCoffeeRepository.getInstance().autoStartVoucherType("0");
		ilaProjectCoffeeRepository.getInstance().autoEndVoucherType("0");
		lstVou.addAttribute("lstVou", ilaProjectCoffeeRepository.getInstance().CallFilter2());
		return "Admin/Share/partialVoucherMg";
	}

	@GetMapping("/filter3")
	public String filter3(Model lstVou) {
		ilaProjectCoffeeRepository.getInstance().autoStartVoucherType("0");
		ilaProjectCoffeeRepository.getInstance().autoEndVoucherType("0");
		lstVou.addAttribute("lstVou", ilaProjectCoffeeRepository.getInstance().CallFilter3());
		return "Admin/Share/partialVoucherMg";
	}

	@PostMapping("/active-voucher")
	public String activeVoucher(@ModelAttribute Voucher updateAct, String idVoucherEnd, BigDecimal conditionEnd,
			int discountEnd, Date startDateEnd, int isActiveEnd) {
		long millis = System.currentTimeMillis();
		Date current = new Date(millis);
		updateAct.setId(idVoucherEnd);
		updateAct.setCondition(conditionEnd);
		updateAct.setDiscount(discountEnd);
		updateAct.setStartDate(startDateEnd);
		updateAct.setIsActive(isActiveEnd);
		updateAct.setEndDate(current);
		ilaProjectCoffeeRepository.getInstance().CallUpdateActiveVoucher(updateAct);
		return "redirect:/Admin/voucher-management";
	}

	@PostMapping("/delete-voucher")
	public String deleteVoucher(@ModelAttribute Voucher modelDlt, String idVoucher) {
		modelDlt.setId(idVoucher);
		ilaProjectCoffeeRepository.getInstance().CallDeleteVoucher(modelDlt);
		return "redirect:/Admin/voucher-management";
	}

	@PostMapping("/update-voucher")
	public String editVoucher(@ModelAttribute Voucher modelEdit, Model lstVou, String idEdit, BigDecimal conditionEdit,
			int discountEdit, Date startDateEdit, Date endDateEdit) {
		modelEdit.setId(idEdit);
		modelEdit.setCondition(conditionEdit);
		modelEdit.setDiscount(discountEdit);
		modelEdit.setStartDate(startDateEdit);
		modelEdit.setEndDate(endDateEdit);

		long millis = System.currentTimeMillis();
		Date current = new Date(millis);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(modelEdit.getStartDate());
		String currentDate = formatter.format(current);
		if (current.before(modelEdit.getStartDate()) == true) {
			modelEdit.setIsActive(0);
		} else if (currentDate.equals(strDate)) {
			modelEdit.setIsActive(1);
		}
		ilaProjectCoffeeRepository.getInstance().CallUpdateVoucher(modelEdit);
		//
		ilaProjectCoffeeRepository.getInstance().autoStartVoucherType("0");
		ilaProjectCoffeeRepository.getInstance().autoEndVoucherType("0");
		lstVou.addAttribute("lstVou", ilaProjectCoffeeRepository.getInstance().CallAllVoucher());
		return "Admin/Share/partialVoucherMg";
	}
}