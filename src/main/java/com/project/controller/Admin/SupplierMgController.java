package com.project.controller.Admin;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.model.HandleInvoiceObject;
import com.project.model.HandleStatusObject;
import com.project.model.InvoiceDetails;
import com.project.model.InvoiceSupplier;
import com.project.model.Supplier;
import com.project.model.SupplierRefundV;
import com.project.modelview.InvoiceView;
import com.project.modelview.ProductView_A;
import com.project.others.SendMail;
import com.project.repository.ilaProjectCoffeeRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;

@Controller
@RequestMapping("/Admin")
public class SupplierMgController {
	@GetMapping("/supplier-management")
	public String dashboard(Model lstSupp, String isActive) {
		ilaProjectCoffeeRepository.getInstance().AD_autoCheckSupplierNotConfirmA();
		ilaProjectCoffeeRepository.getInstance().AD_autoCheckConfirmA();
		if (isActive != null) {
			lstSupp.addAttribute("listSupplier", ilaProjectCoffeeRepository.getInstance().CallAllSupp());
			return "Admin/Share/partialSupplierMg";
		} else {
			lstSupp.addAttribute("listSupplier", ilaProjectCoffeeRepository.getInstance().CallAllSupp());
			return "Admin/supplierMg";
		}
	}

	@PostMapping("/update-supplier")
	public String updateSupp(@ModelAttribute Supplier updateSupp, Model lstSupp, String isActiveFilter)
			throws AddressException, MessagingException, IOException {
		long millis = System.currentTimeMillis();
		Date current = new Date(millis);

		SendMail sendEmail = new SendMail();
		if (updateSupp.getIsActive() == 1) {
			sendEmail.SendMail(updateSupp.getEmail(), "Supplier update notification",
					"Hello. We have received your request to become our supplier. Congratulations on becoming our supplier. Thank you for your interest in us. Happy cooperation. Have a good day!");
			updateSupp.setCreateDate(current);
		} else if (updateSupp.getIsActive() == 2) {
			sendEmail.SendMail(updateSupp.getEmail(), "Supplier update notification",
					"Hello. We have received your request to become our supplier. We're sorry that you're not a good fit for us at iLA. Thank you for your interest in us. See you another time. Have a good day!");
			updateSupp.setCreateDate(current);
		}

		ilaProjectCoffeeRepository.getInstance().CallUpdateSupplier(updateSupp);
		//
		if (isActiveFilter.equals("allSupp")) {
			lstSupp.addAttribute("listSupplier", ilaProjectCoffeeRepository.getInstance().CallAllSupp());
			return "Admin/Share/partialSupplierMg";
		} else {
			lstSupp.addAttribute("listSupplier",
					ilaProjectCoffeeRepository.getInstance().CallFilterSupplier(Integer.parseInt(isActiveFilter)));
			return "Admin/Share/partialSupplierMg";
		}
	}

	@GetMapping("/supplier-management/filterSupp")
	public String filterSupp(Model lstSupp, @RequestParam int isActive) {
		lstSupp.addAttribute("listSupplier", ilaProjectCoffeeRepository.getInstance().CallFilterSupplier(isActive));
		return "Admin/Share/partialSupplierMg";
	}

// prodOfSupp
	@GetMapping("/supplier-management/prodOfSupp")
	public String prodOfSupp(Model lstSupp, @RequestParam int idSupplier, String nameSupp) {
		lstSupp.addAttribute("listProdOfSupp", prodByIdSupp(idSupplier, 0));
		lstSupp.addAttribute("idSupp", idSupplier);

		if (nameSupp.length() > 25) {
			lstSupp.addAttribute("nameSupp", nameSupp.substring(0, 25).concat("..."));
		} else {
			lstSupp.addAttribute("nameSupp", nameSupp);
		}

		return "Admin/prodOfSupp";
	}

	@GetMapping("/supplier-management/prodOfSupp/detail")
	public String detailProd(Model lstSupp, @RequestParam int idSupplier, int idPro) {
		List<ProductView_A> lstProd = prodByIdSupp(idSupplier, 1);
		ProductView_A item = new ProductView_A();
		for (int i = 0; i < lstProd.size(); i++) {
			if (lstProd.get(i).getId() == idPro) {
				item.setId(lstProd.get(i).getId());
				item.setTitle(lstProd.get(i).getTitle());
				item.setDescription(lstProd.get(i).getDescription());
				item.setNameCate(lstProd.get(i).getNameCate());
				item.setPrice(lstProd.get(i).getPrice());
				item.setImage(lstProd.get(i).getImage());
				item.setImage1(lstProd.get(i).getImage1());
				item.setImage2(lstProd.get(i).getImage2());
				item.setImage3(lstProd.get(i).getImage3());
				item.setIsActive(lstProd.get(i).getIsActive());
				item.setSold(lstProd.get(i).getSold());
			}
		}
		lstSupp.addAttribute("detailProd", item);
		return "Admin/Share/partialProdDetail";
	}

	@PostMapping("/supplier-management/update-product")
	public String updateProdSupp(@ModelAttribute ProductView_A updateProd, Model lstSupp) {

		ilaProjectCoffeeRepository.getInstance().CallUpdateProd(updateProd);
		//
		lstSupp.addAttribute("listProdOfSupp", prodByIdSupp(updateProd.getIdSupplier(), 0));
		return "Admin/Share/partialProdOfSupp";
	}

	@GetMapping("/supplier-management/filter")
	public String filterMul(Model lstSupp, @RequestParam int isActive, int idSupplier) {
		lstSupp.addAttribute("listProdOfSupp", prodByActiveAndSupp(isActive, idSupplier, 0));
		return "Admin/Share/partialProdOfSupp";
	}

	private List<ProductView_A> prodByIdSupp(int idSupplier, int dt) {
		List<ProductView_A> ls = new ArrayList<>();
		List<ProductView_A> lstProd = ilaProjectCoffeeRepository.getInstance().CallProductByIdSupp(idSupplier);
		for (int i = 0; i < lstProd.size(); i++) {
			ProductView_A prod = new ProductView_A();
			prod.setId(lstProd.get(i).getId());
			prod.setTitle(lstProd.get(i).getTitle());
			if (lstProd.get(i).getDescription().length() > 25) {
				if (dt == 1) {
					prod.setDescription(lstProd.get(i).getDescription());
				} else {
					prod.setDescription(lstProd.get(i).getDescription().substring(0, 25).concat("..."));
				}
			} else {
				prod.setDescription(lstProd.get(i).getDescription());
			}

			prod.setImage(lstProd.get(i).getImage());
			prod.setImage1(lstProd.get(i).getImage1());
			prod.setImage2(lstProd.get(i).getImage2());
			prod.setImage3(lstProd.get(i).getImage3());
			prod.setPrice(lstProd.get(i).getPrice());
			prod.setIsActive(lstProd.get(i).getIsActive());
			prod.setIdcate(lstProd.get(i).getIdcate());
			prod.setIdSupplier(lstProd.get(i).getIdSupplier());
			prod.setNameCate(lstProd.get(i).getNameCate());
			prod.setNameSupp(lstProd.get(i).getNameSupp());
			List<ProductView_A> sold = ilaProjectCoffeeRepository.getInstance().CallSoldOfProduct(idSupplier);
			for (int j = 0; j < sold.size(); j++) {
				if (sold.get(j).getId() == lstProd.get(i).getId()) {
					prod.setSold(sold.get(j).getSold());
				}
			}
			ls.add(prod);
		}
		return ls;
	}

	private List<ProductView_A> prodByActiveAndSupp(int isActive, int idSupplier, int dt) {
		List<ProductView_A> ls = new ArrayList<>();
		List<ProductView_A> lstProd = ilaProjectCoffeeRepository.getInstance().CallProdByActiveAndSupp(isActive,
				idSupplier);
		for (int i = 0; i < lstProd.size(); i++) {
			ProductView_A prod = new ProductView_A();
			prod.setId(lstProd.get(i).getId());
			prod.setTitle(lstProd.get(i).getTitle());
			if (lstProd.get(i).getDescription().length() > 25) {
				if (dt == 1) {
					prod.setDescription(lstProd.get(i).getDescription());
				} else {
					prod.setDescription(lstProd.get(i).getDescription().substring(0, 25).concat("..."));
				}
			} else {
				prod.setDescription(lstProd.get(i).getDescription());
			}
			prod.setImage(lstProd.get(i).getImage());
			prod.setImage1(lstProd.get(i).getImage1());
			prod.setImage2(lstProd.get(i).getImage2());
			prod.setImage3(lstProd.get(i).getImage3());
			prod.setPrice(lstProd.get(i).getPrice());
			prod.setIsActive(lstProd.get(i).getIsActive());
			prod.setIdcate(lstProd.get(i).getIdcate());
			prod.setIdSupplier(lstProd.get(i).getIdSupplier());
			prod.setNameCate(lstProd.get(i).getNameCate());
			prod.setNameSupp(lstProd.get(i).getNameSupp());
			List<ProductView_A> sold = ilaProjectCoffeeRepository.getInstance().CallSoldOfProduct(idSupplier);
			for (int j = 0; j < sold.size(); j++) {
				if (sold.get(j).getId() == lstProd.get(i).getId()) {
					prod.setSold(sold.get(j).getSold());
				}
			}
			ls.add(prod);
		}
		return ls;
	}

// orderOfSupp
	@GetMapping("/supplier-management/ordersOfSupp")
	public String ordersOfSupp(Model lstSupp, @RequestParam int idSupplier, String nameSupp) {
		lstSupp.addAttribute("listOrdersOfSupp", orderOfSupp(idSupplier));
		lstSupp.addAttribute("idSupp", idSupplier);
		if (nameSupp.length() > 25) {
			lstSupp.addAttribute("nameSupp", nameSupp.substring(0, 25).concat("..."));
		} else {
			lstSupp.addAttribute("nameSupp", nameSupp);
		}
		return "Admin/ordersOfSupp";
	}

	@GetMapping("/supplier-management/filterOrdersOfSupp")
	public String filterOrdersOfSupp(Model lstSupp, @RequestParam int idSupplier, int status) {
		ilaProjectCoffeeRepository.getInstance().AD_autoCheckSupplierNotConfirmA();
		ilaProjectCoffeeRepository.getInstance().AD_autoCheckConfirmA();
		lstSupp.addAttribute("listOrdersOfSupp", filterOrderOfSupp(idSupplier, status));
		lstSupp.addAttribute("idSupp", idSupplier);
		return "Admin/Share/partialOrderOfSupp";
	}

	@GetMapping("/supplier-management/detailOfOrder")
	public String detailOfOrder(Model lstSupp, @RequestParam int idSupplier, int idInv, BigDecimal refundCus,
			String priceVoucherA, int idUser) {
		lstSupp.addAttribute("listDetailOfOrder",
				ilaProjectCoffeeRepository.getInstance().Call_detailOrdersSupp(idSupplier, idInv));
		lstSupp.addAttribute("idSupp", idSupplier);
		lstSupp.addAttribute("idInv", idInv);
		lstSupp.addAttribute("idUser", idUser);

		lstSupp.addAttribute("refundCus", refundCus);
		var quantityProd = ilaProjectCoffeeRepository.getInstance().SelectQuantityInvoice_Ad(idInv);
		lstSupp.addAttribute("quantityProd", quantityProd);
		lstSupp.addAttribute("priceVoucherA", priceVoucherA);

		return "Admin/Share/partialProdOrderSupp";
	}

	// payment For Supp
	@GetMapping("/supplier-management/paymentForSupp")
	public String paymentForSupp(Model lstSupp, InvoiceSupplier modelUpdate, @RequestParam int idSupplier,
			int idInvSup) {
		modelUpdate.setStatus(3);
		modelUpdate.setId(idInvSup);
		ilaProjectCoffeeRepository.getInstance().AdminPaymentForSupp(modelUpdate);

//		if (status == 0 || status == 1) {
//			lstSupp.addAttribute("listOrdersOfSupp", filterOrderOfSupp(idSupplier, Integer.valueOf(status)));
//			lstSupp.addAttribute("idSupp", idSupplier);
//			return "Admin/Share/partialOrderOfSupp";
//		} else {
		lstSupp.addAttribute("listOrdersOfSupp", orderOfSupp(idSupplier));
		lstSupp.addAttribute("idSupp", idSupplier);
		return "Admin/Share/partialOrderOfSupp";
		// }
	}

	// refund To Cus
	@GetMapping("/supplier-management/refundToCus")
	public String refundToCus(Model lstSupp, InvoiceDetails modelUpdate, @RequestParam int idSupplier, int idInv,
			int idUser) {

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
			}
		}

//		if (status == 0 || status == 1) {
//			lstSupp.addAttribute("listOrdersOfSupp", filterOrderOfSupp(idSupplier, Integer.valueOf(status)));
//			lstSupp.addAttribute("idSupp", idSupplier);
//			return "Admin/Share/partialOrderOfSupp";
//		} else {
		lstSupp.addAttribute("listOrdersOfSupp", orderOfSupp(idSupplier));
		lstSupp.addAttribute("idSupp", idSupplier);

		return "Admin/Share/partialOrderOfSupp";
		// }
	}

	@GetMapping("/supplier-management/recMoneyReceived")
	@ResponseBody
	public BigDecimal recMoneyReceived(int idSupplier, int idInv) {
		BigDecimal rec = BigDecimal.ZERO;
		for (InvoiceView item : orderOfSupp(idSupplier)) {
			if (item.getId() == idInv) {
				rec = item.getTotalPrice();
			}
		}
		return rec;
	}

	public List<InvoiceView> orderOfSupp(int idSupplier) {
		List<InvoiceView> ls = new ArrayList<>();
		List<InvoiceView> lstInforCus = ilaProjectCoffeeRepository.getInstance().CallInforCusOrderOfSupp(idSupplier);

		for (int i = 0; i < lstInforCus.size(); i++) {
			InvoiceView item = new InvoiceView();
			item.setId(lstInforCus.get(i).getId());
			item.setIdAccount(lstInforCus.get(i).getIdAccount());
			item.setNameCus(lstInforCus.get(i).getNameCus());
			item.setCreateDate(lstInforCus.get(i).getCreateDate());
			item.setIdInvoiceSup(lstInforCus.get(i).getIdInvoiceSup());
			List<InvoiceView> totalAmount = ilaProjectCoffeeRepository.getInstance()
					.CallGetTotalOrderAmountOfSupp(idSupplier, lstInforCus.get(i).getId());
			for (int j = 0; j < totalAmount.size(); j++) {
				item.setTotalOrderAmount(totalAmount.get(j).getTotalOrderAmount());
			}
			// refund
			List<InvoiceView> refundCus = ilaProjectCoffeeRepository.getInstance()
					.CallTotalPriceProduct_Refund(lstInforCus.get(i).getId(), idSupplier);
			if (refundCus.size() > 0) {
				for (int j = 0; j < refundCus.size(); j++) {
					if (refundCus.get(j).getIsStatus() == 2 || refundCus.get(j).getIsStatus() == 3) {
						item.setRefundtoCustomers(refundCus.get(j).getRefundtoCustomers());
						item.setIsStatus(refundCus.get(j).getIsStatus());
						item.setIdSupplier(refundCus.get(j).getIdSupplier());
					}
				}
			} else {
				item.setRefundtoCustomers(BigDecimal.valueOf(0));
				item.setIsStatus(10);
				item.setIdSupplier(idSupplier);
			}
			//
			List<InvoiceView> moneyReceived = ilaProjectCoffeeRepository.getInstance()
					.CallGetMoneyReceived(lstInforCus.get(i).getStatus(), lstInforCus.get(i).getId(), idSupplier);
			if (moneyReceived != null) {
				for (int j = 0; j < moneyReceived.size(); j++) {
					item.setTotalPrice(moneyReceived.get(j).getTotalPrice());
				}
			} else {
				item.setTotalPrice(BigDecimal.valueOf(0));
			}

			List<InvoiceSupplier> lstStatus = ilaProjectCoffeeRepository.getInstance().CallGetStatus(idSupplier,
					lstInforCus.get(i).getId());
			for (int j = 0; j < lstStatus.size(); j++) {
				// item.setIsStatus(lstStatus.get(j).getSttInvoice());
				item.setIdInvoice(lstStatus.get(j).getSttInvoice());// sttInvoice
				item.setStatus(lstStatus.get(j).getStatus());
			}

			String lstVouS = lstInforCus.get(i).getVoucherS();
			BigDecimal priceVoucherS = ilaProjectCoffeeRepository.getInstance()
					.CallGetPriceVoucherInvoiceSupplier(idSupplier, lstVouS);
			item.setVoucherS(String.valueOf(priceVoucherS));
			// voucherAdm
			var lstPriceVoucherA = ilaProjectCoffeeRepository.getInstance()
					.CallGetUseVoucherILA(lstInforCus.get(i).getVoucherA());
			if (lstPriceVoucherA.size() > 0) {
				item.setPriceVoucherA(BigDecimal.valueOf(ilaProjectCoffeeRepository.getInstance()
						.CallGetUseVoucherILA(lstInforCus.get(i).getVoucherA()).get(0).getDiscount()));
			} else {
				item.setPriceVoucherA(BigDecimal.valueOf(0));
			}

			ls.add(item);
		}
		return ls;
	}

	public List<InvoiceView> filterOrderOfSupp(int idSupplier, int status) {
		List<InvoiceView> ls = new ArrayList<>();
		List<InvoiceView> lstInforCus = ilaProjectCoffeeRepository.getInstance().CallInforCusOrderOfSupp(idSupplier);

		for (int i = 0; i < lstInforCus.size(); i++) {
			if (status == 0) {
				if (lstInforCus.get(i).getStatus() == 0) {
					InvoiceView item = new InvoiceView();
					item.setId(lstInforCus.get(i).getId());
					item.setIdAccount(lstInforCus.get(i).getIdAccount());
					item.setNameCus(lstInforCus.get(i).getNameCus());
					item.setCreateDate(lstInforCus.get(i).getCreateDate());
					item.setIdInvoiceSup(lstInforCus.get(i).getIdInvoiceSup());
					List<InvoiceView> totalAmount = ilaProjectCoffeeRepository.getInstance()
							.CallGetTotalOrderAmountOfSupp(idSupplier, lstInforCus.get(i).getId());
					for (int j = 0; j < totalAmount.size(); j++) {
						item.setTotalOrderAmount(totalAmount.get(j).getTotalOrderAmount());
					}
					// refund
					List<InvoiceView> refundCus = ilaProjectCoffeeRepository.getInstance()
							.CallTotalPriceProduct_Refund(lstInforCus.get(i).getId(), idSupplier);
					if (refundCus.size() > 0) {
						for (int j = 0; j < refundCus.size(); j++) {
							if (refundCus.get(j).getIsStatus() == 2 || refundCus.get(j).getIsStatus() == 3) {
								item.setRefundtoCustomers(refundCus.get(j).getRefundtoCustomers());
								item.setIsStatus(refundCus.get(j).getIsStatus());
								item.setIdSupplier(refundCus.get(j).getIdSupplier());
							}
						}
					} else {
						item.setRefundtoCustomers(BigDecimal.valueOf(0));
						item.setIsStatus(10);
						item.setIdSupplier(idSupplier);
					}
					//
					List<InvoiceView> moneyReceived = ilaProjectCoffeeRepository.getInstance().CallGetMoneyReceived(
							lstInforCus.get(i).getStatus(), lstInforCus.get(i).getId(), idSupplier);
					if (moneyReceived != null) {
						for (int j = 0; j < moneyReceived.size(); j++) {
							item.setTotalPrice(moneyReceived.get(j).getTotalPrice());
						}
					} else {
						item.setTotalPrice(BigDecimal.valueOf(0));
					}

					List<InvoiceSupplier> lstStatus = ilaProjectCoffeeRepository.getInstance().CallGetStatus(idSupplier,
							lstInforCus.get(i).getId());
					for (int j = 0; j < lstStatus.size(); j++) {
						// item.setIsStatus(lstStatus.get(j).getSttInvoice());
						item.setIdInvoice(lstStatus.get(j).getSttInvoice());// sttInvoice
						item.setStatus(lstStatus.get(j).getStatus());
					}

					String lstVouS = lstInforCus.get(i).getVoucherS();
					BigDecimal priceVoucherS = ilaProjectCoffeeRepository.getInstance()
							.CallGetPriceVoucherInvoiceSupplier(idSupplier, lstVouS);
					item.setVoucherS(String.valueOf(priceVoucherS));
					// voucherAdm
					var lstPriceVoucherA = ilaProjectCoffeeRepository.getInstance()
							.CallGetUseVoucherILA(lstInforCus.get(i).getVoucherA());
					if (lstPriceVoucherA.size() > 0) {
						item.setPriceVoucherA(BigDecimal.valueOf(ilaProjectCoffeeRepository.getInstance()
								.CallGetUseVoucherILA(lstInforCus.get(i).getVoucherA()).get(0).getDiscount()));
					} else {
						item.setPriceVoucherA(BigDecimal.valueOf(0));
					}
					ls.add(item);
				}
			} else {
				if (lstInforCus.get(i).getStatus() != 0) {
					InvoiceView item = new InvoiceView();
					item.setId(lstInforCus.get(i).getId());
					item.setIdAccount(lstInforCus.get(i).getIdAccount());
					item.setNameCus(lstInforCus.get(i).getNameCus());
					item.setCreateDate(lstInforCus.get(i).getCreateDate());
					item.setIdInvoiceSup(lstInforCus.get(i).getIdInvoiceSup());
					List<InvoiceView> totalAmount = ilaProjectCoffeeRepository.getInstance()
							.CallGetTotalOrderAmountOfSupp(idSupplier, lstInforCus.get(i).getId());
					for (int j = 0; j < totalAmount.size(); j++) {
						item.setTotalOrderAmount(totalAmount.get(j).getTotalOrderAmount());
					}
					// refund
					List<InvoiceView> refundCus = ilaProjectCoffeeRepository.getInstance()
							.CallTotalPriceProduct_Refund(lstInforCus.get(i).getId(), idSupplier);
					if (refundCus.size() > 0) {
						for (int j = 0; j < refundCus.size(); j++) {
							if (refundCus.get(j).getIsStatus() == 2 || refundCus.get(j).getIsStatus() == 3) {
								item.setRefundtoCustomers(refundCus.get(j).getRefundtoCustomers());
								item.setIsStatus(refundCus.get(j).getIsStatus());
								item.setIdSupplier(refundCus.get(j).getIdSupplier());
							}
						}
					} else {
						item.setRefundtoCustomers(BigDecimal.valueOf(0));
						item.setIsStatus(10);
						item.setIdSupplier(idSupplier);
					}
					//
					List<InvoiceView> moneyReceived = ilaProjectCoffeeRepository.getInstance().CallGetMoneyReceived(
							lstInforCus.get(i).getStatus(), lstInforCus.get(i).getId(), idSupplier);
					if (moneyReceived != null) {
						for (int j = 0; j < moneyReceived.size(); j++) {
							item.setTotalPrice(moneyReceived.get(j).getTotalPrice());
						}
					} else {
						item.setTotalPrice(BigDecimal.valueOf(0));
					}

					List<InvoiceSupplier> lstStatus = ilaProjectCoffeeRepository.getInstance().CallGetStatus(idSupplier,
							lstInforCus.get(i).getId());
					for (int j = 0; j < lstStatus.size(); j++) {
						// item.setIsStatus(lstStatus.get(j).getSttInvoice());
						item.setIdInvoice(lstStatus.get(j).getSttInvoice());// sttInvoice
						item.setStatus(lstStatus.get(j).getStatus());
					}

					String lstVouS = lstInforCus.get(i).getVoucherS();
					BigDecimal priceVoucherS = ilaProjectCoffeeRepository.getInstance()
							.CallGetPriceVoucherInvoiceSupplier(idSupplier, lstVouS);
					item.setVoucherS(String.valueOf(priceVoucherS));
					// voucherAdm
					var lstPriceVoucherA = ilaProjectCoffeeRepository.getInstance()
							.CallGetUseVoucherILA(lstInforCus.get(i).getVoucherA());
					if (lstPriceVoucherA.size() > 0) {
						item.setPriceVoucherA(BigDecimal.valueOf(ilaProjectCoffeeRepository.getInstance()
								.CallGetUseVoucherILA(lstInforCus.get(i).getVoucherA()).get(0).getDiscount()));
					} else {
						item.setPriceVoucherA(BigDecimal.valueOf(0));
					}
					ls.add(item);
				}
			}
		}
		return ls;
	}

	// check Refund To Supp
	@GetMapping("/supplier-management/priceRefundWhenInv9")
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

	@GetMapping("/supplier-management/refundToSupp")
	@ResponseBody
	public Boolean refundToSupp(Model lstData, InvoiceSupplier modelUpdate, @RequestParam int idSupplier, int idInv) {
		boolean check = false;
		try {
			ilaProjectCoffeeRepository.getInstance().AdminRefundToSupp(idSupplier, idInv);
			check = true;
		} catch (Exception e) {
			// TODO: handle exception
			check = false;
		}
		return check;
	}
}
