package com.project.controller.Supplier;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.calling.BatchCalling;
import com.project.model.HandleStatusObject;
import com.project.model.InvoiceSupplier;
import com.project.model.Supplier;
import com.project.model.Voucher;
import com.project.modelview.InvoiceManagement13Field;
import com.project.modelview.InvoiceView;
import com.project.others.Encode_Decode;
import com.project.repository.ilaProjectCoffeeRepository;
import com.project.utils.StringValue;


@Controller
@RequestMapping("/Supplier")
public class InvoiceManagementController {
	private BigDecimal zero = new BigDecimal(0);

	public InvoiceView setDataNullStringOrZero() {
		// set default
		// set "" - 0
		InvoiceView invoi = new InvoiceView();
		invoi.setId(0);
		invoi.setNameCus("");
		invoi.setAddress("");
		invoi.setIsStatus(0);
		invoi.setTotalAmountOfProduct(zero);
		invoi.setTotalOrderAmount(zero);
		invoi.setRefundtoCustomers(zero);
		//
		// end
		return invoi;
	}
	//format
	private String formatBigDecimal(BigDecimal number) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
        return df.format(number);
    }
	// invoice Default
	private InvoiceManagement13Field setDefault() {
		InvoiceManagement13Field list = new InvoiceManagement13Field();
		return list;
	}
	// 20/01/24
	public void Call2FunctionBeforeLoadInvoiceManagement(int idSupplier) {
		BatchCalling.getInstance().Supp_AutoCheckConfirmS(idSupplier);
		BatchCalling.getInstance().Supp_AutoCheckSupplierNotConfirmS(idSupplier);
		System.out.println("running task: call 2fx before load invoice managment");
	}
	//
	@RequestMapping(value = "/index-invoice-management", method = RequestMethod.GET) 
	public String InvoiceIndex(@CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier, 
			@CookieValue(value = "suppCookie", defaultValue = "0") String suppCookie,Model model) {
		//co khi login vao
		Call2FunctionBeforeLoadInvoiceManagement(idSupplier);
		List<InvoiceView> list;
		list = collapseListInvoiceMg(idSupplier);
		model.addAttribute("collapseListInvoiceMgI", list);
		
		//============ Thuan Fix Avatar, Ten Cua Supp ============
		String decodeSupp = Encode_Decode.getInstance().decodeString(suppCookie);
		Supplier supplier = (Supplier) Encode_Decode.getInstance().jsonToObject(decodeSupp, Supplier.class);

		model.addAttribute("suppCookie", supplier);	
		return "/Supplier/InvoiceManagement3";
	}
	
	//invoice detail
	@RequestMapping(value = "/index-invoice-management/invoice-detail", method = RequestMethod.GET)
	public String InvoiceDetail(@CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier,Model model, @RequestParam int idOfInvoice) {
		System.out.println("log data idInvoice: "+idOfInvoice);

		//co khi login vao
		//1
		//int [] statusOfInvoiceDetail;
		ArrayList<Integer> Ls_StatusOfInvoiceDetail = new ArrayList<>();
		boolean _flagCheckStatusInvoiceDetail = false; 
	    List<InvoiceView> listInvoiceDetail = getInvoiceDetail(idSupplier, idOfInvoice);
	    for (InvoiceView invoi: listInvoiceDetail) {
			invoi.setPrice(new BigDecimal(formatBigDecimal(invoi.getPrice())));
			invoi.setPriceOrder(new BigDecimal(formatBigDecimal(invoi.getPriceOrder())));
			System.out.println("ra: "+invoi.getPrice()+" | "+invoi.getPriceOrder()+" | "+invoi.getStatus()+" | "+invoi.getIsStatus());
			//
			Ls_StatusOfInvoiceDetail.add(invoi.getIsStatus());
			
		}
	    for (int status : Ls_StatusOfInvoiceDetail) {
			System.out.println("data log status of invoiceDetail : "+status);
			if(status == 1 ||status ==2 ||status ==3 ||status ==4 ||status ==9) {
				_flagCheckStatusInvoiceDetail = true;
			}
		}
	    System.out.println("data log hide btn "+_flagCheckStatusInvoiceDetail);
	    //2
		List<InvoiceManagement13Field> list = dataProcessing13Filead(idOfInvoice, idSupplier);
		int statusOfInvoiceSupp = list.get(0).getStatusOfInvoiceSupp();
		model.addAttribute("invoiceDetailMgI", list);
		model.addAttribute("invoiceDetail", listInvoiceDetail);
		model.addAttribute("statusOfInvoiceSupp", statusOfInvoiceSupp);
		model.addAttribute("diableBtnConfirm", _flagCheckStatusInvoiceDetail);
		return "/Supplier/Layout/PartialInvoiceDetailSup";
	}
	//
	
	private List<InvoiceView> collapseListInvoiceMg(int idSupp){
		List<InvoiceView> list = ilaProjectCoffeeRepository.getInstance().getNameCus_Address_CreateAt(idSupp);
		return list;
	}
	// xu ly main
	private List<InvoiceManagement13Field> dataProcessing13Filead(int idInvoi, int idSupplier) {
		int index = 0;
		int idSupp = idSupplier;
		List<InvoiceView> ListRoot= getNameCus_Address_CreateAt(idSupp);
	    InvoiceView set_default = new InvoiceView();
	    set_default.setId(idInvoi);
	    
		
		ArrayList<InvoiceManagement13Field> listMaster = new ArrayList<InvoiceManagement13Field>();
		ArrayList<Integer> arrIdInvoice = new ArrayList<Integer>();
		try {

			for (InvoiceView obj : ListRoot) {
				if(obj.getId() == set_default.getId()) {
					//fix indexs =00
					arrIdInvoice.add(obj.getId());
					listMaster.add(setDefault());
					listMaster.get(0).setIdInvoice(obj.getId());
					listMaster.get(0).setIdInvoiceSupp(obj.getIdInvoiceSup());
					listMaster.get(0).setNameCus(obj.getNameCus());
					listMaster.get(0).setAddress(obj.getAddress());
					listMaster.get(0).setCreateAt(obj.getCreateDate());
					listMaster.get(0).setVoucher(obj.getVoucherS());	
					listMaster.get(0).setDateMaxC(obj.getDateMaxC());
				}	
				index++;
			}
			
			index = 0;
			for (int idInvoice : arrIdInvoice) {
				InvoiceManagement13Field objOfListMaster; // = new InvoiceManagement13Field();
				objOfListMaster = listMaster.get(index);
				//
				ArrayList<InvoiceSupplier> status = (ArrayList<InvoiceSupplier>) getActionOrder(idInvoice, idSupp);
				if (!status.isEmpty()) {
					objOfListMaster.setStatusOfInvoice(status.get(0).getSttInvoice());
					objOfListMaster.setStatusOfInvoiceSupp(status.get(0).getStatus());
				}
				//priceVoucher
				BigDecimal priceVoucher = zero;
				System.out.println("log code voucher: "+objOfListMaster.getVoucher());
				priceVoucher = getPriceVoucher(idSupp, objOfListMaster.getVoucher());
				System.out.println("log priceVoucher: "+priceVoucher);
				objOfListMaster.setPriceVoucher(new BigDecimal(formatBigDecimal(priceVoucher)));

				// -original invoice
				BigDecimal totalOrderAmount = zero;
				ArrayList<InvoiceView> totalOrderAmountList = (ArrayList<InvoiceView>) getTotalOrderAmount(idSupp, idInvoice);
				totalOrderAmount =totalOrderAmountList.get(0).getTotalOrderAmount();
				objOfListMaster.setTotalOrderAmount(new BigDecimal(formatBigDecimal(totalOrderAmount)));
				
				// -totalAmountOfProduct --bo
				BigDecimal totalAmountOfProduct = zero;
				BigDecimal TotalAmountOfProductCoppyValue;
				ArrayList<InvoiceView> totalAmountOfProductList = (ArrayList<InvoiceView>) GetTotalAmountOfProduct(
						idSupp, idInvoice);
				totalAmountOfProduct = (totalAmountOfProductList.isEmpty() || listMaster.get(index).getStatusOfInvoiceSupp() ==0) ? zero
						: totalAmountOfProductList.get(0).getTotalAmountOfProduct();
				objOfListMaster.setTotalAmountOfProduct(new BigDecimal(formatBigDecimal(totalAmountOfProduct)));
				//copy value
				
				if(listMaster.get(index).getStatusOfInvoiceSupp() ==0) {
					TotalAmountOfProductCoppyValue = zero;
				}else {					
					TotalAmountOfProductCoppyValue =totalOrderAmount;
				}
				
				//--commission 
				BigDecimal Commission = zero;		
				int percentage = 10;
				Commission = TotalAmountOfProductCoppyValue.multiply(BigDecimal.valueOf((double) percentage / 100));
				objOfListMaster.setCommission(new BigDecimal(formatBigDecimal(Commission)));
				//
				//
				// - totalAmountOfProduct |fix 6/1
				BigDecimal estimatedTotalPrice = zero;
				BigDecimal priceVou = listMaster.get(index).getPriceVoucher();
					estimatedTotalPrice = TotalAmountOfProductCoppyValue.subtract(
						TotalAmountOfProductCoppyValue.multiply(BigDecimal.valueOf((double) percentage / 100))).subtract(priceVou);
					    int _flagCheckEstimatedTotalPrice =estimatedTotalPrice.compareTo(zero) ;
						if( _flagCheckEstimatedTotalPrice >=0) {
							
							objOfListMaster.setEstimatedTotalPrice(new BigDecimal(formatBigDecimal(estimatedTotalPrice)));
						}else {
							objOfListMaster.setEstimatedTotalPrice(new BigDecimal(formatBigDecimal(zero)));	
						}
	
				//getPriceRefundAdmin
				
				BigDecimal _priceRefundAdmin = zero;
				_priceRefundAdmin = Sup_getPriceRefundAdmin(idSupp, idInvoice);
				objOfListMaster.setRefundtoCustomers(_priceRefundAdmin);
								
				//
				BigDecimal moneyReceived = zero;
				ArrayList<InvoiceView> moneyReceivedList = (ArrayList<InvoiceView>) GetMoneyReceived(objOfListMaster.getStatusOfInvoiceSupp(), idInvoice, idSupp);
				moneyReceived = (moneyReceivedList.isEmpty()) ? new BigDecimal(formatBigDecimal(zero)) : moneyReceivedList.get(0).getTotalPrice();
				objOfListMaster.setMoneyReceived(new BigDecimal(formatBigDecimal(moneyReceived)));

				//
				// log data test

				//end
				index++;
			}
			// data log test:
			// Tiêu đề của bảng
		        System.out.println("+-----------+-----------+-------------+-------------+----------------+-------------+-----------+-----------+---------------+--------------+--------------+--------------+");
		        System.out.println("| Name Cus  |Id Invoice |Id InvoiSupp |Status Invoi |Status InvoiSup |Voucher code |Actual inv |Comission  |Monry received |Price voucher |Refund to ad  |Original inv  |");
		        System.out.println("+-----------+-----------+-------------+-------------+----------------+-------------+-----------+-----------+---------------+--------------+--------------+--------------+");
			for (InvoiceManagement13Field obj : listMaster) {
		        // Dữ liệu của bảng
		        System.out.println("| "+obj.getNameCus() +"      |      "+obj.getIdInvoice() +"    |         "+obj.getIdInvoiceSupp() +"   |   "
		        		+obj.getStatusOfInvoice() +"         |   "+obj.getStatusOfInvoiceSupp() +"            |   "+obj.getVoucher() +"    |   "+obj.getEstimatedTotalPrice() +"    |   "
		        		+obj.getCommission() +"    | "+obj.getMoneyReceived() +"       |   "+obj.getPriceVoucher() +"      |   "+obj.getRefundtoCustomers()+"       |       "
		        		+obj.getTotalOrderAmount() +" |   ");
			}
			
				System.out.println("+-----------+-----------+-------------+-------------+----------------+-------------+-----------+-----------+---------------+--------------+--------------+--------------+");
			//end
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("err??? :" + e.getMessage());
		}
		
		return listMaster;
	}
	//
	@GetMapping("/getNameCus-Address-CreateAt")
	// @ResponseBody
	public List<InvoiceView> getNameCus_Address_CreateAt(int idSupplier) {
		List<InvoiceView> list = null;
		list = ilaProjectCoffeeRepository.getInstance().getNameCus_Address_CreateAt(idSupplier); // new
		if (list != null) {
			return list;
		}

		return null;
	}

	// getNameCus-address-createAt return -> json
	@GetMapping("/getNameCus-Address-CreateAt-Json")
	@ResponseBody
	public List<InvoiceView> getNameCus_Address_CreateAtJson(int idSupplier) {
		List<InvoiceView> list = null;
		list = ilaProjectCoffeeRepository.getInstance().getNameCus_Address_CreateAt(idSupplier);
		if (list != null) {
			return list;
		}

		return null;
	}

	@GetMapping("/getTotalOrderAmount")
	// @ResponseBody
	public List<InvoiceView> getTotalOrderAmount(int idSupplier, int idInvoice) {
		List<InvoiceView> list = null;
		list = ilaProjectCoffeeRepository.getInstance().getTotalOrderAmount(idSupplier, idInvoice); // new
		if (list != null) {
			return list;
		}
		return (List<InvoiceView>) setDataNullStringOrZero();
	}

	@GetMapping("/getTotalOrderAmount-Json")
	@ResponseBody
	public List<InvoiceView> getTotalOrderAmountJson(int idSupplier, int idInvoice) {
		List<InvoiceView> list = null;
		list = ilaProjectCoffeeRepository.getInstance().getTotalOrderAmount(idSupplier, idInvoice);
		if (list != null) {
			return list;
		}
		return null;
	}

	@GetMapping("/GetTotalAmountOfProduct")
	// @ResponseBody
	public List<InvoiceView> GetTotalAmountOfProduct(int idSupplier, int idInvoice) {
		List<InvoiceView> list = new ArrayList<InvoiceView>();
		list = ilaProjectCoffeeRepository.getInstance().GetTotalAmountOfProduct(idSupplier, idInvoice);
		if (list != null) {
			return list;
		} else {
			return (List<InvoiceView>) setDataNullStringOrZero();
		}

	}

	@GetMapping("/GetRefundtoCustomers")
	// @ResponseBody
	public List<InvoiceView> GetRefundtoCustomers(int idSupplier, int idInvoice) {
		List<InvoiceView> list = new ArrayList<InvoiceView>();
		list = ilaProjectCoffeeRepository.getInstance().GetRefundtoCustomers(idSupplier, idInvoice);
		if (list != null) {
			return list;
		}
		return (List<InvoiceView>) setDataNullStringOrZero();
	}

	@GetMapping("/GetMoneyReceived")
	// @ResponseBody
	public List<InvoiceView> GetMoneyReceived(int status, int idInvoice, int idSupplier) {
		List<InvoiceView> list = new ArrayList<InvoiceView>();
		list = ilaProjectCoffeeRepository.getInstance().GetMoneyReceived(status, idSupplier, idInvoice);
		if (list != null) {
			return list;
		}
		return null;
	}


	// confirm -- unConfirm --disable
	@GetMapping("/confirm-unconfirm-invoice")
	@ResponseBody 
	public ResponseEntity<String> changeStatusInvoice(int userCase, int statusType, int idSupplier, int idInvoice, int idInvoiceDetails) {
		boolean checkFlag = false;
		try {
			HandleStatusObject object = new HandleStatusObject();
			object.setUserCase(userCase);
			object.setStatusType(statusType);
			object.setIdSupplier(idSupplier);
			object.setIdInvoice(idInvoice);
			object.setIdInvoiceDetails(idInvoiceDetails);

			System.out.println(userCase + " " + statusType + " " + idSupplier + " " + idInvoice + " " + idInvoiceDetails);
			checkFlag = ilaProjectCoffeeRepository.getInstance().confirmInvoiceDetail(object);
			if (checkFlag) {
				System.out.println("ok");
				return ResponseEntity.ok("success");
			} else {
				System.out.println("err");
				return ResponseEntity.status(500).body("filed");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("err? : "+e.getMessage());
		}
		return ResponseEntity.status(500).body("filed");
	}

	// getActionOrder
	@GetMapping("/get-action-order")
	public List<InvoiceSupplier> getActionOrder(int id, int idSupplier) {
		InvoiceSupplier listDataNull = new InvoiceSupplier();
		List<InvoiceSupplier> list = ilaProjectCoffeeRepository.getInstance().getActionOrder(id, idSupplier);
		if (!list.isEmpty()) {
			return list;
		}
		return (List<InvoiceSupplier>) listDataNull;
	}

	// get priceVoucher
	//@GetMapping("/get-price-voucher")
	public BigDecimal getPriceVoucher(int idSupplier, String idVoucher) {

		BigDecimal pv = ilaProjectCoffeeRepository.getInstance().getPriceVoucher(idSupplier, idVoucher);
		if (pv != null) {
			return pv;
		}
		BigDecimal zero = new BigDecimal(0);
		return zero;
	}

	// confrim invoi master -disable
	@PostMapping("/confirm-invoiM")
	@ResponseBody
	public ResponseEntity ConfirmInvoiM(InvoiceSupplier is) {
		boolean flagCheck = ilaProjectCoffeeRepository.getInstance().ConfirmInvoiM(is);
		try {
			if (flagCheck) {
				System.out.println("confirm invoice success");
				return ResponseEntity.ok("success");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("confirm invoice failed "+e.getMessage());
		}
		return ResponseEntity.badRequest().body("failed");
	}
	
	//-----------------------
	@GetMapping("/get-invoice-detail")
	//@ResponseBody
	public List<InvoiceView> getInvoiceDetail(int idSupplier, int idOfInvoice) {
		List<InvoiceView> list = null;
		list = ilaProjectCoffeeRepository.getInstance().GetInvoiceDetail(idSupplier, idOfInvoice);
		if (list != null) {
			return list;
		}

		return null;
	}
	//new 27-12
	@GetMapping("/prepare-invoice-detail")
	@ResponseBody
	public String prepareInvoice(@CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier
			,@RequestParam int idInvoice,@RequestParam String strLsInvoiD,@RequestParam String strLsStatus) {
		//idSupp login
		System.out.println("data log: pre-invoice-detail "+idSupplier+" "+idInvoice+" "+strLsInvoiD+" "+strLsStatus);
		String result = ilaProjectCoffeeRepository.getInstance().prepareInvoice(idSupplier, idInvoice, strLsInvoiD, strLsStatus);
		
		//
		System.out.println("data log result prepare-invoiceDetail: "+result);
		return result;
	}
	//
	//new 2024
	@RequestMapping(value = "/supp-confirm-refund", method = RequestMethod.GET)
	@ResponseBody
	public String supConfirmRefund(@CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier,int idInvoice ) {
		//login
		System.out.println("log data supConfirm: "+idInvoice+" "+idSupplier);
		boolean flagCheck = false;
		flagCheck = ilaProjectCoffeeRepository.getInstance().supConfirmRefund(idInvoice, idSupplier);
		if(flagCheck) {
			return "ok";
		};
		return "faild";
	}
	//end
	
	//@RequestMapping(value ="") |disable
	public BigDecimal GetPriceVoucher2(String id, String userCreate) {
		Voucher v = ilaProjectCoffeeRepository.getInstance().GetPriceVoucher(id, userCreate);
		BigDecimal priceVoucher = new BigDecimal(v.getDiscount()) ;
		return priceVoucher;
	}
	//end
	@RequestMapping(value="/sup-confirm-adPayment")
	@ResponseBody
	public boolean Sup_ConfirmReceivedMoneyOfAd(@CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier,int idInvoice, int idSupp) {
		//login 
		HandleStatusObject suppConfrim = new HandleStatusObject();
		suppConfrim.setIdInvoice(idInvoice);
		suppConfrim.setIdSupplier(idSupplier);
		suppConfrim.setIdInvoiceDetails(0);
		suppConfrim.setStatusType(2);
		suppConfrim.setUserCase(1);
		System.out.println("log data: "+suppConfrim);
		return ilaProjectCoffeeRepository.getInstance().Sup_ConfirmReceivedMoneyOfAd(suppConfrim);
	}
	//5 pointer
	//7-1
	private BigDecimal Sup_getPriceRefundAdmin(int idSupp, int idInvoice) {
		BigDecimal _result = ilaProjectCoffeeRepository.getInstance().Sup_getPriceRefundAdmin(idSupp, idInvoice) ;
		if(_result == zero) {
			return BigDecimal.ZERO;
		}
		return _result;
	}
}
