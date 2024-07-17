package com.project.controller.Admin;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.analysis.function.Subtract;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.model.Account;
import com.project.model.HandleStatusObject;
import com.project.model.InvoiceDetails;
import com.project.modelview.InvoiceDetailsU;
import com.project.modelview.InvoiceView;
import com.project.repository.ilaProjectCoffeeRepository;

@Controller
@RequestMapping("/Admin")
public class CustomerMgController {

	@GetMapping("/customer-management")
	public String dashboard(Model lstCus) {
		lstCus.addAttribute("listCustomer", ilaProjectCoffeeRepository.getInstance().CallAllAccount());
		return "Admin/customerMg";
	}

	@PostMapping("/customer-management/update-customer")
	public String updateCus(@ModelAttribute Account updateCus, int isActive, int idEdit) {
		updateCus.setId(idEdit);
		if (isActive == 0) {
			updateCus.setActive(false);
		} else {
			updateCus.setActive(true);
		}
		ilaProjectCoffeeRepository.getInstance().CallUpdateAccount(updateCus);
		return "redirect:/Admin/customer-management";
	}

	@GetMapping("/customer-management/customerOrder")
	public String customerOrder(Model lstCus, int idAccount) {
		ilaProjectCoffeeRepository.getInstance().AD_autoCheckSupplierNotConfirmA();
		ilaProjectCoffeeRepository.getInstance().AD_autoCheckConfirmA();
		// goi api ~ don hang cua cus
		lstCus.addAttribute("listCusOrder", orderOfCus(idAccount));
		lstCus.addAttribute("idAccount", idAccount);
		return "Admin/customerOrders";
	}

	private List<InvoiceView> orderOfCus(int idAccount) {
		List<InvoiceView> ls = new ArrayList<>();
		var lstOrderOfCus = ilaProjectCoffeeRepository.getInstance().AD_CallOrderOfCus(idAccount);
		for (int i = 0; i < lstOrderOfCus.size(); i++) {
			InvoiceView item = new InvoiceView();

			item.setIdInvoiceSup(lstOrderOfCus.get(i).getIdInvoiceSup());
			item.setIdInvoice(lstOrderOfCus.get(i).getIdInvoice());
			item.setVoucherS(lstOrderOfCus.get(i).getVoucherS());
			item.setVoucherA(lstOrderOfCus.get(i).getVoucherA());
			item.setTitleSup(lstOrderOfCus.get(i).getTitleSup());
			// item.setPriceInvSup(lstOrderOfCus.get(i).getPriceInvSup());
			item.setSttInvSup(lstOrderOfCus.get(i).getSttInvSup());
			item.setIdSupplier(lstOrderOfCus.get(i).getIdSupplier());
			item.setIsStatus(lstOrderOfCus.get(i).getIsStatus());
			// voucherAdm
			BigDecimal priceVoucherA = BigDecimal.ZERO;
			var lstPriceVoucherA = ilaProjectCoffeeRepository.getInstance()
					.CallGetUseVoucherILA(lstOrderOfCus.get(i).getVoucherA());
			if (lstPriceVoucherA.size() > 0) {
				item.setPriceVoucherA(BigDecimal.valueOf(ilaProjectCoffeeRepository.getInstance()
						.CallGetUseVoucherILA(lstOrderOfCus.get(i).getVoucherA()).get(0).getDiscount()));
				priceVoucherA = BigDecimal.valueOf(ilaProjectCoffeeRepository.getInstance()
						.CallGetUseVoucherILA(lstOrderOfCus.get(i).getVoucherA()).get(0).getDiscount());
			} else {
				item.setPriceVoucherA(BigDecimal.valueOf(0));
			}
			//
			// ----- totalPrice
			item.setPriceInvSup(priceInvSupp(lstOrderOfCus.get(i).getIdInvoiceSup(),
					lstOrderOfCus.get(i).getIdSupplier(), lstOrderOfCus.get(i).getIdInvoice(), priceVoucherA,
					lstOrderOfCus.get(i).getIsStatus(), lstOrderOfCus.get(i).getSttInvSup()));

			ls.add(item);
		}
		return ls;
	}

	private BigDecimal priceInvSupp(int idInvSupp, int idSupplier, int idInvoice, BigDecimal priceVoucherA, int sttInv,
			int sttInvSupp) {
		BigDecimal amount = BigDecimal.ZERO;
		BigDecimal priceVoucherS = BigDecimal.ZERO;
		BigDecimal refund = BigDecimal.ZERO;

		BigDecimal totalPayment = BigDecimal.ZERO;

		if (sttInv == 9 && sttInvSupp == 9) {
			totalPayment = BigDecimal.ZERO;
		} else if (sttInv != 9 && sttInvSupp == 9) {
			var lst = ilaProjectCoffeeRepository.getInstance().AD_CallProduct_OrderOfCus(idInvSupp, idSupplier);

			List<InvoiceView> totalAmount = ilaProjectCoffeeRepository.getInstance()
					.CallGetTotalOrderAmountOfSupp(idSupplier, idInvoice);
			for (int j = 0; j < totalAmount.size(); j++) {
				amount = totalAmount.get(j).getTotalOrderAmount();
				break;
			}
			var refundLst = ilaProjectCoffeeRepository.getInstance().CallTotalPriceProduct_Refund(idInvoice,
					idSupplier);
			for (int i = 0; i < refundLst.size(); i++) {
				if (refundLst.get(i).getIsStatus() == 2 || refundLst.get(i).getIsStatus() == 3) {
					refund = refundLst.get(i).getRefundtoCustomers();
					break;
				}
			}
			List<InvoiceView> lstInforCus = ilaProjectCoffeeRepository.getInstance()
					.CallInforCusOrderOfSupp(idSupplier);

			for (int i = 0; i < lstInforCus.size(); i++) {
				if (lstInforCus.get(i).getIdInvoiceSup() == idInvSupp) {
					String lstVouS = lstInforCus.get(i).getVoucherS();
					priceVoucherS = ilaProjectCoffeeRepository.getInstance()
							.CallGetPriceVoucherInvoiceSupplier(idSupplier, lstVouS);
					break;
				}
			}

			BigDecimal fee = BigDecimal.valueOf(0.02).multiply((amount.subtract(priceVoucherS)));
			totalPayment = amount.subtract(refund).add(fee);
		} else {
			var lst = ilaProjectCoffeeRepository.getInstance().AD_CallProduct_OrderOfCus(idInvSupp, idSupplier);

			List<InvoiceView> totalAmount = ilaProjectCoffeeRepository.getInstance()
					.CallGetTotalOrderAmountOfSupp(idSupplier, idInvoice);
			for (int j = 0; j < totalAmount.size(); j++) {
				amount = totalAmount.get(j).getTotalOrderAmount();
				break;
			}
			var refundLst = ilaProjectCoffeeRepository.getInstance().CallTotalPriceProduct_Refund(idInvoice,
					idSupplier);
			for (int i = 0; i < refundLst.size(); i++) {
				if (refundLst.get(i).getIsStatus() == 2 || refundLst.get(i).getIsStatus() == 3) {
					refund = refundLst.get(i).getRefundtoCustomers();
					break;
				}
			}
			List<InvoiceView> lstInforCus = ilaProjectCoffeeRepository.getInstance()
					.CallInforCusOrderOfSupp(idSupplier);

			for (int i = 0; i < lstInforCus.size(); i++) {
				if (lstInforCus.get(i).getIdInvoiceSup() == idInvSupp) {
					String lstVouS = lstInforCus.get(i).getVoucherS();
					priceVoucherS = ilaProjectCoffeeRepository.getInstance()
							.CallGetPriceVoucherInvoiceSupplier(idSupplier, lstVouS);
					break;
				}
			}
			BigDecimal fee = BigDecimal.valueOf(0.02).multiply((amount.subtract(priceVoucherS)));
			if (amount.subtract(refund) == BigDecimal.ZERO) {
				totalPayment = amount.subtract(refund).add(fee);
			} else {
				BigDecimal quantity = BigDecimal
						.valueOf(ilaProjectCoffeeRepository.getInstance().SelectQuantityInvoice_Ad(idInvoice));
				// calculator
				if (priceVoucherA != BigDecimal.ZERO) {
					var priceVouAofSupp = (priceVoucherA.divide(quantity, 2, RoundingMode.HALF_UP))
							.multiply(BigDecimal.valueOf(lst.size()));

					if (refund != BigDecimal.ZERO) {
						totalPayment = amount.subtract(priceVoucherS).subtract(priceVouAofSupp).add(fee)
								.subtract(refund);
					} else {
						totalPayment = amount.subtract(priceVoucherS).subtract(priceVouAofSupp).add(fee);
					}
				} else {
					totalPayment = amount.subtract(priceVoucherS).add(fee);
				}
			}

		}

		return totalPayment;
	}

	@GetMapping("/customer-management/purchasedProduct")
	public String purchasedProduct(Model lstCus, int id, int idAccount, int idSupplier, int idInvoice,
			BigDecimal priceVouA, int sttInv, int sttInvSupp) {
		// goi api ~ san pham cua don hang do
		lstCus.addAttribute("listCusOrderProd",
				ilaProjectCoffeeRepository.getInstance().AD_CallProduct_OrderOfCus(id, idSupplier));
		lstCus.addAttribute("idAccount", idAccount);
		List<InvoiceView> totalAmount = ilaProjectCoffeeRepository.getInstance()
				.CallGetTotalOrderAmountOfSupp(idSupplier, idInvoice);
		for (int j = 0; j < totalAmount.size(); j++) {
			lstCus.addAttribute("totalAmount", totalAmount.get(j).getTotalOrderAmount());
		}
		var refundLst = ilaProjectCoffeeRepository.getInstance().CallTotalPriceProduct_Refund(idInvoice, idSupplier);
		for (int i = 0; i < refundLst.size(); i++) {
			if (refundLst.get(i).getIsStatus() == 2 || refundLst.get(i).getIsStatus() == 3) {
				var refund = refundLst.get(i).getRefundtoCustomers();
				lstCus.addAttribute("refund", refund);
				lstCus.addAttribute("sttRefund", refundLst.get(i).getIsStatus());
			}
		}
		List<InvoiceView> lstInforCus = ilaProjectCoffeeRepository.getInstance().CallInforCusOrderOfSupp(idSupplier);

		for (int i = 0; i < lstInforCus.size(); i++) {
			if (lstInforCus.get(i).getIdInvoiceSup() == id) {
				String lstVouS = lstInforCus.get(i).getVoucherS();
				BigDecimal priceVoucherS = ilaProjectCoffeeRepository.getInstance()
						.CallGetPriceVoucherInvoiceSupplier(idSupplier, lstVouS);
				lstCus.addAttribute("priceVoucherS", String.valueOf(priceVoucherS));
			}
		}

		int quantity = ilaProjectCoffeeRepository.getInstance().SelectQuantityInvoice_Ad(idInvoice);
		lstCus.addAttribute("quantity", quantity);
		lstCus.addAttribute("priceVouA", priceVouA);
		lstCus.addAttribute("idSupplier", idSupplier);
		lstCus.addAttribute("idInvoice", idInvoice);
		lstCus.addAttribute("idInvSupp", id);
		lstCus.addAttribute("sttInv", sttInv);
		lstCus.addAttribute("sttInvSupp", sttInvSupp);
		return "Admin/purchasedProd";
	}

	@GetMapping("/customer-management/refundToCus")
	public String refundToCus(Model lstCus, InvoiceDetails modelUpdate, @RequestParam int idSupplier, int idInv,
			int idAccount, int idInvSupp, BigDecimal priceVouA, int sttInv, int sttInvSupp) {

		var detailOrder = ilaProjectCoffeeRepository.getInstance().AD_CallProduct_OrderOfCus(idInvSupp, idSupplier);
		for (int i = 0; i < detailOrder.size(); i++) {
			if (detailOrder.get(i).getSttInvDetail() == 2) {
				HandleStatusObject hso = new HandleStatusObject();
				hso.setUserCase(0);
				hso.setStatusType(0);
				hso.setIdSupplier(idSupplier);
				hso.setIdInvoice(idInv);
				hso.setIdInvoiceDetails(detailOrder.get(i).getIdInvoiceDetail());
				hso.setIdUser(idAccount);

				ilaProjectCoffeeRepository.getInstance().userConfirmInvoiceDetails(hso);
			}
		}

		// goi api ~ san pham cua don hang do
		lstCus.addAttribute("listCusOrderProd",
				ilaProjectCoffeeRepository.getInstance().AD_CallProduct_OrderOfCus(idInvSupp, idSupplier));
		lstCus.addAttribute("idAccount", idAccount);
		List<InvoiceView> totalAmount = ilaProjectCoffeeRepository.getInstance()
				.CallGetTotalOrderAmountOfSupp(idSupplier, idInv);
		for (int j = 0; j < totalAmount.size(); j++) {
			lstCus.addAttribute("totalAmount", totalAmount.get(j).getTotalOrderAmount());
		}
		var refundLst = ilaProjectCoffeeRepository.getInstance().CallTotalPriceProduct_Refund(idInv, idSupplier);
		for (int i = 0; i < refundLst.size(); i++) {
			if (refundLst.get(i).getIsStatus() == 2 || refundLst.get(i).getIsStatus() == 3) {
				var refund = refundLst.get(i).getRefundtoCustomers();
				lstCus.addAttribute("refund", refund);
				lstCus.addAttribute("sttRefund", refundLst.get(i).getIsStatus());
			}
		}
		List<InvoiceView> lstInforCus = ilaProjectCoffeeRepository.getInstance().CallInforCusOrderOfSupp(idSupplier);

		for (int i = 0; i < lstInforCus.size(); i++) {
			if (lstInforCus.get(i).getIdInvoiceSup() == idInvSupp) {
				String lstVouS = lstInforCus.get(i).getVoucherS();
				BigDecimal priceVoucherS = ilaProjectCoffeeRepository.getInstance()
						.CallGetPriceVoucherInvoiceSupplier(idSupplier, lstVouS);
				lstCus.addAttribute("priceVoucherS", String.valueOf(priceVoucherS));
			}
		}

		int quantity = ilaProjectCoffeeRepository.getInstance().SelectQuantityInvoice_Ad(idInv);
		lstCus.addAttribute("quantity", quantity);
		lstCus.addAttribute("priceVouA", priceVouA);
		lstCus.addAttribute("idSupplier", idSupplier);
		lstCus.addAttribute("idInvoice", idInv);
		lstCus.addAttribute("idInvSupp", idInvSupp);
		lstCus.addAttribute("sttInv", sttInv);
		lstCus.addAttribute("sttInvSupp", sttInvSupp);
		return "Admin/purchasedProd";
	}
	
	@GetMapping("/customer-management/checkInvAfterRefundCus")
	@ResponseBody
	public int checkInvAfterRefundCus(int idAccount,int idInvSupp) {
		var lst = orderOfCus(idAccount);
		var sttInv =0;
		for (InvoiceView item : lst) {
			if(item.getIdInvoiceSup()==idInvSupp) {
				sttInv = item.getIsStatus();
			}
		}
		return sttInv;
	}

}
