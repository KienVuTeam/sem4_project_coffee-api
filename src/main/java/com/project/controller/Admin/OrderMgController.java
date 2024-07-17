package com.project.controller.Admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.model.HandleStatusObject;
import com.project.model.InvoiceAdmin;
import com.project.model.InvoiceDetails;
import com.project.model.InvoiceSupplier;
import com.project.model.SupplierRefundV;
import com.project.model.Voucher;
import com.project.modelview.InvoiceView;
import com.project.repository.ilaProjectCoffeeRepository;

@Controller
@RequestMapping("/Admin")
public class OrderMgController {
	@GetMapping("/order-management")
	public String dashboard(Model lstData) {
		ilaProjectCoffeeRepository.getInstance().AD_autoCheckSupplierNotConfirmA();
		ilaProjectCoffeeRepository.getInstance().AD_autoCheckConfirmA();
		lstData.addAttribute("lstViewMain", viewMain());
		return "Admin/orderMg";
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
				List<Voucher> useVoucherA = ilaProjectCoffeeRepository.getInstance()
						.CallGetUseVoucherILA(getCus.get(i).getVoucherA());
				for (int j = 0; j < useVoucherA.size(); j++) {
					itemMain.setPriceVoucherA(BigDecimal.valueOf(useVoucherA.get(j).getDiscount()));
				}
				itemMain.setVoucherS(getCus.get(i).getVoucherS());
				itemMain.setTotalPrice(getCus.get(i).getTotalPrice());
				itemMain.setIsStatus(getCus.get(i).getIsStatus());
				lstMain.add(itemMain);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return lstMain;
	}

	@GetMapping("/order-management/detailOrder")
	public String detailOfOrder(Model lstData, int idInv, String lstVoucherS, String priceVoucherA, int idUser) {
		lstData.addAttribute("lstViewSub", viewSub(idInv, lstVoucherS));
		lstData.addAttribute("idInv", idInv);
		lstData.addAttribute("lstVoucherS", lstVoucherS);
		lstData.addAttribute("priceVoucherA", priceVoucherA);
		lstData.addAttribute("idUser", idUser);

		return "Admin/Share/orderDetail";
	}

	private List<InvoiceView> viewSub(int idInv, String lstVoucherS) {
		List<InvoiceView> lstSub = new ArrayList<>();
		try {
			List<InvoiceView> lstSupp = ilaProjectCoffeeRepository.getInstance().CallTotalPriceInvoiceSupplier(idInv);
			for (int i = 0; i < lstSupp.size(); i++) {
				InvoiceView itemSub = new InvoiceView();
				itemSub.setIdSupplier(lstSupp.get(i).getIdSupplier());
				if (lstSupp.get(i).getTitleSup().length() > 20) {
					itemSub.setTitleSup(lstSupp.get(i).getTitleSup().substring(0, 20).concat("..."));
				} else {
					itemSub.setTitleSup(lstSupp.get(i).getTitleSup());
				}

				itemSub.setTotalOrderAmount(lstSupp.get(i).getTotalOrderAmount());
				List<InvoiceView> lstRefund = ilaProjectCoffeeRepository.getInstance()
						.CallTotalPriceProduct_Refund(idInv, lstSupp.get(i).getIdSupplier());
				if (lstRefund.size() > 0) {
					for (int j = 0; j < lstRefund.size(); j++) {
						if (lstRefund.get(j).getIsStatus() == 2 || lstRefund.get(j).getIsStatus() == 3) {
							itemSub.setRefundtoCustomers(lstRefund.get(j).getRefundtoCustomers());
							itemSub.setIsStatus(lstRefund.get(j).getIsStatus());
							break;
						}
					}
				} else {
					itemSub.setRefundtoCustomers(BigDecimal.valueOf(0));
					itemSub.setIsStatus(10);
				}

				BigDecimal priceVoucherS = ilaProjectCoffeeRepository.getInstance()
						.CallGetPriceVoucherInvoiceSupplier(lstSupp.get(i).getIdSupplier(), lstVoucherS);
				itemSub.setVoucherS(String.valueOf(priceVoucherS));

				// stt InvSup
				List<InvoiceSupplier> lstStatus = ilaProjectCoffeeRepository.getInstance()
						.CallGetStatus(lstSupp.get(i).getIdSupplier(), idInv);
				for (int j = 0; j < lstStatus.size(); j++) {
					itemSub.setStatus(lstStatus.get(j).getStatus());

					// price paymentForSupp
					List<InvoiceView> paymentForSupp = ilaProjectCoffeeRepository.getInstance()
							.CallGetMoneyReceived(lstStatus.get(j).getStatus(), idInv, lstSupp.get(i).getIdSupplier());
					if (paymentForSupp.size() > 0) {
						for (int k = 0; k < paymentForSupp.size(); k++) {
							itemSub.setTotalPrice(paymentForSupp.get(k).getTotalPrice());
						}
					} else {
						itemSub.setTotalPrice(BigDecimal.valueOf(0));
					}

					// price RefundSuppVou When Status = 9
					if (lstStatus.get(j).getStatus() == 9) {
						BigDecimal priceVouRefundSupp = ilaProjectCoffeeRepository.getInstance()
								.Sup_getPriceRefundAdmin(lstSupp.get(i).getIdSupplier(), idInv);
						itemSub.setPriceRefundSuppV(priceVouRefundSupp);
					}
				}

				lstSub.add(itemSub);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return lstSub;
	}

	@GetMapping("/order-management/priceRefundWhenInv9")
	@ResponseBody
	public SupplierRefundV priceRefundWhenInv9(int idSupplier, int idInv) {
		BigDecimal priceVouRefundSupp = ilaProjectCoffeeRepository.getInstance().Sup_getPriceRefundAdmin(idSupplier,
				idInv);
		// call api get sttSupplierRefundV
		// checkRefund 0 la chua refund
		var checkRefund = 0;
		var theList = ilaProjectCoffeeRepository.getInstance().AdminGetSupplierRefundByInvAndIdSup(idInv, idSupplier);
		for (int i = 0; i < theList.size(); i++) {
			if (theList.get(i).getStatus() == 2) {
				checkRefund = 1;
				break;
			}
		}
		SupplierRefundV refundV = new SupplierRefundV();
		refundV.setPriceRefund(priceVouRefundSupp);
		refundV.setStatus(checkRefund);
		return refundV;
	}

	@GetMapping("/order-management/checkRefundWhenInv9")
	@ResponseBody
	public SupplierRefundV checkRefundWhenInv9(int idInv) {
		// call api get sttSupplierRefundV
		// checkRefund 0 la chua refund
		var suppRefund = 0;
		var checkRefund = 0;
		var theList = ilaProjectCoffeeRepository.getInstance().AdminGetSupplierRefundByInv(idInv);
		if (theList.size() > 0) {
			for (int i = 0; i < theList.size(); i++) {
				if (theList.get(i).getStatus() == 2) {
					checkRefund = 1;
				} else {
					suppRefund = theList.get(i).getIdSupplier();
				}
			}
		}

		SupplierRefundV refundV = new SupplierRefundV();
		refundV.setIdSupplier(suppRefund);
		refundV.setStatus(checkRefund);
		return refundV;
	}

	@GetMapping("/order-management/detailOrderBySupp")
	public String detailOrderBySupp(Model lstData, int idSupplier, int idInv, String lstVoucherS, String priceVoucherA,
			int idUser) {
		var lstTempOrder = viewSub(idInv, lstVoucherS);
		for (int i = 0; i < lstTempOrder.size(); i++) {
			if (lstTempOrder.get(i).getIdSupplier() == idSupplier) {
				lstData.addAttribute("moneyOfSupp", lstTempOrder.get(i).getTotalPrice());
				break;
			}
		}

		lstData.addAttribute("lstDetailOrderBySupp",
				ilaProjectCoffeeRepository.getInstance().Call_detailOrdersSupp(idSupplier, idInv));
		lstData.addAttribute("idInv", idInv);
		lstData.addAttribute("idSupp", idSupplier);
		lstData.addAttribute("lstVoucherS", lstVoucherS);

		var quantityProd = ilaProjectCoffeeRepository.getInstance().SelectQuantityInvoice_Ad(idInv);
		lstData.addAttribute("quantityProd", quantityProd);
		lstData.addAttribute("priceVoucherA", priceVoucherA);
		lstData.addAttribute("idUser", idUser);
		return "Admin/Share/orderDetailBySupp";
	}

	@GetMapping("/order-management/paymentForSupp")
	public Boolean paymentForSupp(Model lstData, InvoiceSupplier modelUpdate, @RequestParam int idSupplier, int idInv,
			String lstVoucherS, String priceVoucherA, int idUser) {
		boolean check = false;
		var lstOrderOfCus = ilaProjectCoffeeRepository.getInstance().AD_CallOrderOfCus(idUser);
		for (int i = 0; i < lstOrderOfCus.size(); i++) {
			if (lstOrderOfCus.get(i).getIdInvoice() == idInv && lstOrderOfCus.get(i).getIdSupplier() == idSupplier) {
				modelUpdate.setStatus(3);
				modelUpdate.setId(lstOrderOfCus.get(i).getIdInvoiceSup());
				ilaProjectCoffeeRepository.getInstance().AdminPaymentForSupp(modelUpdate);
				check = true;
			}
		}
		return check;
	}

	@GetMapping("/order-management/refundToCus")
	@ResponseBody
	public InvoiceView refundToCus(Model lstData, @RequestParam int idSupplier, int idInv, int idUser,
			String lstVoucherS, String priceVoucherA) {
		var check = 0;
		var detailOrder = ilaProjectCoffeeRepository.getInstance().Call_detailOrdersSupp(idSupplier, idInv);
		for (int i = 0; i < detailOrder.size(); i++) {
			if (detailOrder.get(i).getIsStatus() == 2) {
				HandleStatusObject hso = new HandleStatusObject();
				hso.setUserCase(0);
				hso.setStatusType(0);
				hso.setIdSupplier(idSupplier);
				hso.setIdInvoice(idInv);
				hso.setIdInvoiceDetails(detailOrder.get(i).getIdInvoiceDetail());
				hso.setIdUser(idUser);

				ilaProjectCoffeeRepository.getInstance().userConfirmInvoiceDetails(hso);
				check = 1;
			}
		}

		InvoiceView nInvV = new InvoiceView();
		var lstTempOrder = viewSub(idInv, lstVoucherS);
		for (int i = 0; i < lstTempOrder.size(); i++) {
			if (lstTempOrder.get(i).getIdSupplier() == idSupplier) {
				nInvV.setPriceInvSup(lstTempOrder.get(i).getTotalPrice());
			}
		}
		nInvV.setSttInvSup(check);
		return nInvV;

	}

	@GetMapping("/order-management/refundToSupp")
	@ResponseBody
	public Boolean refundToSupp(Model lstData, InvoiceSupplier modelUpdate, @RequestParam int idSupplier, int idInv,
			String lstVoucherS, String priceVoucherA, int idUser) {
		boolean check = false;
		try {
			ilaProjectCoffeeRepository.getInstance().AdminRefundToSupp(idSupplier, idInv);
			check = true;
		} catch (Exception e) {
			// TODO: handle exception
			check = false;
		}
		return check;

//		var lstTempOrder = viewSub(idInv, lstVoucherS);
//		for (int i = 0; i < lstTempOrder.size(); i++) {
//			if (lstTempOrder.get(i).getIdSupplier() == idSupplier) {
//				lstData.addAttribute("moneyOfSupp", lstTempOrder.get(i).getTotalPrice());
//				break;
//			}
//		}
//
//		lstData.addAttribute("lstDetailOrderBySupp",
//				ilaProjectCoffeeRepository.getInstance().Call_detailOrdersSupp(idSupplier, idInv));
//		lstData.addAttribute("idInv", idInv);
//		lstData.addAttribute("idSupp", idSupplier);
//		lstData.addAttribute("lstVoucherS", lstVoucherS);
//
//		var quantityProd = ilaProjectCoffeeRepository.getInstance().SelectQuantityInvoice_Ad(idInv);
//		lstData.addAttribute("quantityProd", quantityProd);
//		lstData.addAttribute("priceVoucherA", priceVoucherA);
//		lstData.addAttribute("idUser", idUser);
//		return "Admin/Share/orderDetailBySupp";
	}

	@GetMapping("/order-management/refundToAllSupp")
	public String refundToAllSupp(Model lstData, int idInv, String lstVoucherS, String priceVoucherA, int idUser) {

		ilaProjectCoffeeRepository.getInstance().AdminRefundToAllSupp(idInv);
		//
		lstData.addAttribute("lstViewSub", viewSub(idInv, lstVoucherS));
		lstData.addAttribute("idInv", idInv);
		lstData.addAttribute("lstVoucherS", lstVoucherS);
		lstData.addAttribute("priceVoucherA", priceVoucherA);
		lstData.addAttribute("idUser", idUser);
		return "Admin/Share/orderDetail";
	}

	@GetMapping("/order-management/checkRefundFee")
	@ResponseBody
	public Boolean checkRefundFee(Model lstData, int idInvoice) {
		boolean checkRefund = false;

		var lst = ilaProjectCoffeeRepository.getInstance().CallActionOrder(idInvoice);

		for (InvoiceAdmin item : lst) {
			if (item.getFeeService().toString().equals("0.00")) {
				checkRefund = true;
			}
		}
		return checkRefund;
	}

	@GetMapping("/order-management/refundFeeToCus")
	@ResponseBody
	public Boolean refundFeeToCus(Model lstData, int idInvoice) {
		boolean checkRefund = false;
		try {
			ilaProjectCoffeeRepository.getInstance().AdminRefundFeeToCus(idInvoice);
			checkRefund = true;
		} catch (Exception e) {
			// TODO: handle exception
			checkRefund = false;
			System.out.println("err refundFeeToCus");
		}
		return checkRefund;
	}
}
