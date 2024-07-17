package com.project.calling;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.project.model.HandleInvoiceObject;
import com.project.model.HandleStatusObject;
import com.project.model.StatusInvoice;
import com.project.model.WorkPDFRevenueM;
import com.project.modelview.InvoiceDetailsU;
import com.project.modelview.InvoiceView;
import com.project.modelview.InvoiceViewU;
import com.project.modelview._dllTestInv;
import com.project.utils.BaseUrl;

public class InvoiceCalling {
	private static InvoiceCalling instance = null;
	private RestTemplate restTemplate;

	public static InvoiceCalling getInstance() {
		if (instance == null) {
			instance = new InvoiceCalling();
		}
		return instance;
	}

	/**
	 * @author Vinh
	 */

	public boolean userPaymentInvoice(HandleInvoiceObject invoiceObject) {
		boolean flagPayment = true;
		try {
			restTemplate = new RestTemplate();
			URI uri = new URI(BaseUrl.US_PAYMENT_INV);
			ResponseEntity<Boolean> result = restTemplate.postForEntity(uri, invoiceObject, Boolean.class);
			flagPayment = result.getBody();
		} catch (Exception e) {
			flagPayment = false;
			//
			System.out.println("ERROR userPaymentInvoice");
			System.out.println(e.getMessage());
		}
		return flagPayment;
	}

	public InvoiceViewU userGetInfoInvoice(int idInvoice) {
		InvoiceViewU ivU = new InvoiceViewU();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_GETINFO_INV.replace("queryParam", String.valueOf(idInvoice));
			ResponseEntity<InvoiceViewU> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<InvoiceViewU>() {
					});
			//
			ivU = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR userPaymentInvoice");
			System.out.println(e.getMessage());
		}
		return ivU;
	}

	public boolean userConfirmInvoiceDetails(HandleStatusObject objectHandle) {
		Boolean flagConfirm = true;
		try {
			restTemplate = new RestTemplate();
			URI uri = new URI(BaseUrl.US_CONFIRM_INVD);
			ResponseEntity<Boolean> result = restTemplate.postForEntity(uri, objectHandle, boolean.class);
			flagConfirm = result.getBody();
		} catch (Exception e) {
			System.out.println("ERROR userConfirmInvoiceDetails");
			System.out.println(e.getMessage());
		}
		return flagConfirm;
	}

	public List<_dllTestInv> dllAdminInvoice(int idInvoice) {
		List<_dllTestInv> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<_dllTestInv>> res = restTemplate.exchange(
					BaseUrl.INVOICEDLL_ADMIN.replace("paramInvoice", String.valueOf(idInvoice)), HttpMethod.GET, null,
					new ParameterizedTypeReference<List<_dllTestInv>>() {
					});
			ls = res.getBody();
		} catch (Exception e) {
			System.out.println("ERROR dllAdminInvoice");
			System.out.println(e.getMessage());
		}
		return ls;
	}

	public List<_dllTestInv> dllSupplierInvoice(int idInvoice, int idSupplier) {
		List<_dllTestInv> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			String urlS = BaseUrl.INVOICEDLL_SUPP.replace("paramInvoice", String.valueOf(idInvoice))
					.replace("paramSupp", String.valueOf(idSupplier));
			ResponseEntity<List<_dllTestInv>> res = restTemplate.exchange(urlS, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<_dllTestInv>>() {
					});
			ls = res.getBody();
		} catch (Exception e) {
			System.out.println("ERROR dllSupplierInvoice");
			System.out.println(e.getMessage());
		}
		return ls;
	}

	public List<InvoiceViewU> userGetAllInvoice(int idAccount) {
		List<InvoiceViewU> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			String urlS = BaseUrl.US_GETALL_INV.replace("queryParam", String.valueOf(idAccount));
			ResponseEntity<List<InvoiceViewU>> res = restTemplate.exchange(urlS, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<InvoiceViewU>>() {
					});
			ls = res.getBody();
		} catch (Exception e) {
			System.out.println("ERROR userGetAllInvoice");
			System.out.println(e.getMessage());
		}
		return ls;
	}

	public List<InvoiceViewU> userSearchInvoice(int idAccount, String codeSearch) {
		List<InvoiceViewU> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			String urlS = BaseUrl.US_SEARCH_INV.replace("queryId", String.valueOf(idAccount)).replace("querySearch",
					codeSearch);
			ResponseEntity<List<InvoiceViewU>> res = restTemplate.exchange(urlS, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<InvoiceViewU>>() {
					});
			ls = res.getBody();
		} catch (Exception e) {
			System.out.println("ERROR userGetAllInvoice");
			System.out.println(e.getMessage());
		}
		return ls;
	}

	public BigDecimal userGetFeeService(int idInvoice) {
		BigDecimal feeService = BigDecimal.ZERO;
		try {
			restTemplate = new RestTemplate();
			String urlS = BaseUrl.US_GETFEE_IV.replace("queryInvoice", String.valueOf(idInvoice));
			ResponseEntity<BigDecimal> res = restTemplate.exchange(urlS, HttpMethod.GET, null,
					new ParameterizedTypeReference<BigDecimal>() {
					});
			feeService = res.getBody();
		} catch (Exception e) {
			System.out.println("ERROR userGetFeeService");
			System.out.println(e.getMessage());
		}
		return feeService;
	}
	
	public List<InvoiceViewU> getInvoiceForOutputExcel(int Month, int Year){
		List<InvoiceViewU> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			String urlS = BaseUrl.GET_DATA_OUEXCEL.replace("queryParam00", String.valueOf(Month)).replace("queryParam01", String.valueOf(Year));
			ResponseEntity<List<InvoiceViewU>> res = restTemplate.exchange(urlS, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<InvoiceViewU>>() {
					});
			ls = res.getBody();
		} catch (Exception e) {
			System.out.println("ERROR getInvoiceForOutputExcel");
			System.out.println(e.getMessage());
		}
		return ls;
	}
	
	public List<InvoiceViewU> getChartsRevenueAdmin(int Month, int Year){
		List<InvoiceViewU> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			String urlS = BaseUrl.GET_CHARTS_ADMIN.replace("queryParam00", String.valueOf(Month)).replace("queryParam01", String.valueOf(Year));
			ResponseEntity<List<InvoiceViewU>> res = restTemplate.exchange(urlS, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<InvoiceViewU>>() {
					});
			ls = res.getBody();
		} catch (Exception e) {
			System.out.println("ERROR getChartsRevenueAdmin");
			System.out.println(e.getMessage());
		}
		return ls;
	}
	
	public List<WorkPDFRevenueM> getInvoiceForOutputPDF(int Month, int Year){
		List<WorkPDFRevenueM> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			String urlS = BaseUrl.GET_DATA_OUTPDF.replace("queryParam00", String.valueOf(Month)).replace("queryParam01", String.valueOf(Year));
			ResponseEntity<List<WorkPDFRevenueM>> res = restTemplate.exchange(urlS, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<WorkPDFRevenueM>>() {
					});
			ls = res.getBody();
		} catch (Exception e) {
			System.out.println("ERROR getInvoiceForOutputPDF");
			System.out.println(e.getMessage());
		}
		return ls;
	}

	/**
	 * @author MaiTran
	 */
	public List<InvoiceView> CallGetName_Phone_Date_TotalInvoice() {
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<InvoiceView>> res = restTemplate.exchange(BaseUrl.AD_GETNAME_PHONE_DATE_TOTALINVOICE,
					HttpMethod.GET, null, new ParameterizedTypeReference<List<InvoiceView>>() {
					});
			return res.getBody();

		} catch (Exception e) {
			System.out.println("ERROR CallGetName_Phone_Date_TotalInvoice");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<InvoiceView> CallTotalPriceInvoiceSupplier(int idInvoice) {
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<InvoiceView>> res = restTemplate.exchange(BaseUrl.AD_GETTOTALPRICE_INVSUPP.concat("?idInvoice="+idInvoice),
					HttpMethod.GET, null, new ParameterizedTypeReference<List<InvoiceView>>() {
					});
			return res.getBody();

		} catch (Exception e) {
			System.out.println("ERROR CallTotalPriceInvoiceSupplier");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<InvoiceView> CallTotalPriceProduct_Refund(int idInvoice, int idSupplier) {
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<InvoiceView>> res = restTemplate.exchange(
					BaseUrl.AD_GETTOTALPRICE_PROD_REFUND
							.concat("?idInvoice=" + idInvoice + "&idSupplier=" + idSupplier),
					HttpMethod.GET, null, new ParameterizedTypeReference<List<InvoiceView>>() {
					});
			return res.getBody();

		} catch (Exception e) {
			System.out.println("ERROR CallTotalPriceProduct_Refund");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<InvoiceView> CallGetDetailOrderSup(int id, int idSupplier) {
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<InvoiceView>> res = restTemplate.exchange(BaseUrl.AD_GETDETAILORDERSUPP, HttpMethod.GET,
					null, new ParameterizedTypeReference<List<InvoiceView>>() {
					});
			return res.getBody();

		} catch (Exception e) {
			System.out.println("ERROR CallActionOrder");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<InvoiceView> AD_CallOrderOfCus(int idAccount) {
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<InvoiceView>> res = restTemplate.exchange(
					BaseUrl.AD_ORDEROFCUS.concat("?idAccount=" + idAccount), HttpMethod.GET, null,
					new ParameterizedTypeReference<List<InvoiceView>>() {
					});
			return res.getBody();

		} catch (Exception e) {
			System.out.println("ERROR AD_CallOrderOfCus");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<InvoiceView> AD_CallProduct_OrderOfCus(int id, int idSupplier) {
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<InvoiceView>> res = restTemplate.exchange(
					BaseUrl.AD_PRODUCT_ORDEROFCUS.concat("?id=" + id + "&idSupplier=" + idSupplier), HttpMethod.GET,
					null, new ParameterizedTypeReference<List<InvoiceView>>() {
					});
			return res.getBody();

		} catch (Exception e) {
			System.out.println("ERROR AD_CallProduct_OrderOfCus");
			System.out.println(e.getMessage());
		}
		return null;
	}

}
