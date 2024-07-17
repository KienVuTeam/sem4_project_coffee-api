package com.project.calling;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Collections;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.project.model.Blog;
import com.project.model.HandleStatusObject;
import com.project.model.InvoiceSupplier;
import com.project.model.Voucher;
import com.project.modelview.InvoiceView;
//import com.project.modelView.InvoiceView;
import com.project.utils.BaseUrl;

public class CallingInvoice_Sup {

	RestTemplate restTemplate;
	private static CallingInvoice_Sup instance = null;

	private CallingInvoice_Sup() {
	}

	public static CallingInvoice_Sup getInstance() {
		if (instance == null) {
			instance = new CallingInvoice_Sup();
		}
		return instance;
	}

	// --------------------------
	// invoice management 08-10
	public List<InvoiceView> getNameCus_Address_CreateAt(int idSupplier) {
		String urlAPI = BaseUrl.Sup_NameCus_Address_CreateAt.replace("param1", String.valueOf(idSupplier));
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<InvoiceView>> resp = restTemplate.exchange(urlAPI, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<InvoiceView>>() {
					});
			if (resp.getStatusCode().is2xxSuccessful()) {
				return resp.getBody();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public List<InvoiceView> getTotalOrderAmount(int idSupplier, int idInvoice) {
		String urlAPI = BaseUrl.Sup_GetTotalOrderAmount.replace("param1", String.valueOf(idSupplier)).replace("param2",
				String.valueOf(idInvoice));
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<InvoiceView>> resp = restTemplate.exchange(urlAPI, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<InvoiceView>>() {
					});
			if (resp.getStatusCode().is2xxSuccessful()) {
				return resp.getBody();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	

	public List<InvoiceView> GetTotalAmountOfProduct(int idSupplier, int idInvoice) {
		String urlAPI = BaseUrl.Sup_GetTotalAmountOfProduct.replace("param1", String.valueOf(idSupplier)).replace("param2", String.valueOf(idInvoice)) ;
		//String urlAPI = BaseUrl.Sup_GetTotalAmountOfProduct.replace(0, 0);)
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<InvoiceView>> resp = restTemplate.exchange(urlAPI, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<InvoiceView>>() {
					});
			if (resp.getStatusCode().is2xxSuccessful()) {
				return resp.getBody();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	public List<InvoiceView> GetRefundtoCustomers(int idSupplier, int idInvoice) {
		String urlAPI = BaseUrl.Sup_GetRefundtoCustomers.replace("param1", String.valueOf(idSupplier)).replace("param2",String.valueOf(idInvoice) );
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<InvoiceView>> resp = restTemplate.exchange(urlAPI, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<InvoiceView>>() {
					});
			if (resp.getStatusCode().is2xxSuccessful()) {
				return resp.getBody();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public List<InvoiceView> GetMoneyReceived(int status, int idSupplier, int idInvoice) {
		String urlAPI = BaseUrl.Sup_GetMoneyReceived.replace("param1", String.valueOf(status)).replace("param2", String.valueOf(idInvoice)).replace("param3",
				String.valueOf(idSupplier));
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<InvoiceView>> resp = restTemplate.exchange(urlAPI, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<InvoiceView>>() {
					});
			if (resp.getStatusCode().is2xxSuccessful()) {
				return resp.getBody();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	// 14-10
	public List<InvoiceView> GetInvoiceDetail(int idSupplier, int id) {
		String urlAPI = BaseUrl.Sup_getInvoiceDetail.replace("param1", String.valueOf(id)).replace("param2",
				String.valueOf(idSupplier));
		
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<InvoiceView>> resp = restTemplate.exchange(urlAPI, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<InvoiceView>>() {
					});
			if (resp.getStatusCode().is2xxSuccessful()) {
				return resp.getBody();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// 19-10
	public boolean confirmInvoiceDetail(HandleStatusObject object) {
//		String urlAPI = BaseUrl.Sup_confirmInvoiceDetail.replace("param1", String.valueOf(object.getUserCase()))
//				.replace("param2", String.valueOf(object.getStatusType()))
//				.replace("param3", String.valueOf(object.getIdSupplier()))
//				.replace("param4", String.valueOf(object.getIdInvoice()))
//				.replace("param5", String.valueOf(object.getIdInvoiceDetails()));
		// String urlAPI =BaseUrl.Sup_confirmInvoiceDetail;
		try {
			restTemplate = new RestTemplate();
//			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(urlAPI)
//		            .queryParam("userCase", object.getUserCase())
//		            .queryParam("statusType", object.getStatusType())
//		            .queryParam("idSupplier", object.getIdSupplier())
//		            .queryParam("idInvoice", object.getIdInvoice())
//		            .queryParam("idInvoiceDetails", object.getIdInvoiceDetails());

			// Gọi API với URL đã xây dựng và phương thức GET
			// String url = builder.toUriString();
			// String resp = restTemplate.getForObject(url, String.class);
			// HandleStatusObject result = restTemplate.getForObject(urlAPI,
			// HandleStatusObject.class);
			// System.out.println("co run here");
			// System.out.println("run? "+result);
			// return true;
			//
			// RequestEntity<?> requestEntity = RequestEntity.get(new URI(urlAPI)).build();
			//
			// ResponseEntity<HandleStatusObject> resp = restTemplate.exchange(urlAPI,
			// HttpMethod.GET, null,
			// HandleStatusObject.class);
			// ResponseEntity<HandleStatusObject> resp =
			// restTemplate.exchange(requestEntity, HandleStatusObject.class);
			// c n+1
			// Tạo đối tượng HttpEntity để đặt tham số trong request body
			// HttpEntity<HandleStatusObject> requestEntity = new HttpEntity<>(object);

			// URL của API
			// String apiUrl = "https://example.com/api/resource"; // Thay thế bằng URL của
			// API thực tế
//c Vinh
			// Gọi API với phương thức POST và requestEntity
//			HttpHeaders headers = new HttpHeaders();								
//			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));														
//			String aaa = "http://localhost:8081/api/Invoice/statusOfInvoice";
//			 URI uri = new URI(aaa);
//			HttpEntity<HandleStatusObject> req = new HttpEntity<HandleStatusObject>(object,headers);
//			ResponseEntity<Boolean> resp = restTemplate.exchange(uri, HttpMethod.GET, req,
//					new ParameterizedTypeReference<Boolean>() {
//					});
			//end Vinh
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			// Create HttpEntity with the Book object and headers
			HttpEntity<HandleStatusObject> requestEntity = new HttpEntity<>(object, headers);
			RestTemplate restTemplate = new RestTemplate();										
			URI uri = new URI(BaseUrl.Sup_confirmInvoiceDetail);										
			ResponseEntity<String> resp = restTemplate.postForEntity(uri, requestEntity, String.class);										
			if(resp.getStatusCode().is2xxSuccessful()) {
				System.out.println("ok");
				return true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("err: " + e.getMessage());
		}
		return false;
	}
	
	public List<InvoiceSupplier> getActionOrder(int idInvoi, int idSup){
		String urlAPI =BaseUrl.Sup_GetActionOrder.replace("param1", String.valueOf(idInvoi)).replace("param2", String.valueOf(idSup));
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<InvoiceSupplier>> resp = restTemplate.exchange(urlAPI, HttpMethod.GET,null,
					new ParameterizedTypeReference<List<InvoiceSupplier>>() {
					});
			if(resp.getStatusCode().is2xxSuccessful()) {
				return resp.getBody();
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("err: "+e.getMessage());
		}
		return null;
	}
	public BigDecimal getPriceVoucher(int idSupplier, String idVoucher) {
		BigDecimal zero = new BigDecimal(0);
		String urlAPi =BaseUrl.Sup_GetPriceVoucherNew.replace("param1", String.valueOf(idSupplier)).replace("param2", String.valueOf(idVoucher));
		
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<BigDecimal> resp = restTemplate.exchange(urlAPi, HttpMethod.GET,null,
					new ParameterizedTypeReference<BigDecimal>() {
					});
			if(resp.getStatusCode().is2xxSuccessful()) {
				System.out.println("");
				return resp.getBody();
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Err: "+e.getMessage());
		}
		return zero;
	}
	//0911
	public boolean ConfirmInvoiM(InvoiceSupplier is) {
		//set status of invoiSup =1;
		is.setStatus(1);
		String urlAPI =BaseUrl.Sup_confirmInvoiceM;
		
		try {
			restTemplate = new RestTemplate();
			ResponseEntity resp = restTemplate.postForEntity(urlAPI, is, String.class);
			if(resp.getStatusCode().is2xxSuccessful()) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("err: "+e);
		}
		
		return false;
	}
	
	//branch fix
	public InvoiceView getTotalOrderAmount_Object(int idSupplier, int idInvoice) {
		String urlAPI = BaseUrl.Sup_GetTotalOrderAmount.replace("param1", String.valueOf(idSupplier)).replace("param2",
				String.valueOf(idInvoice));
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<InvoiceView> resp = restTemplate.exchange(urlAPI, HttpMethod.GET, null,
					new ParameterizedTypeReference<InvoiceView>() {
					});
			if (resp.getStatusCode().is2xxSuccessful()) {
				return resp.getBody();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
	//end fix
	//27-12
	public String prepareInvoice(int idSupplier, int idInvoice, String strLsInvoiD, String strLsStatus) {
		String urlAPI = BaseUrl.Sup_prepareInvoice.replace("param1", String.valueOf(idSupplier)).replace("param2", String.valueOf(idInvoice)).replace("param3", String.valueOf(strLsInvoiD)).replace("param4", String.valueOf(strLsStatus));
		try {
			restTemplate = new RestTemplate();
			String resp = restTemplate.getForObject(urlAPI, String.class);
			return resp;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("err for prepare invoice:"+e.getMessage());
		}
		return null;
		
	}
	public boolean supConfirmRefund(int idInvoice, int idSupplier) {
		String urlAPI = BaseUrl.Sup_ConfirmRefund.replace("param1", String.valueOf(idInvoice)).replace("param2", String.valueOf(idSupplier));
		try {
			restTemplate = new RestTemplate();
			boolean resp = restTemplate.getForObject(urlAPI, Boolean.class);
			return resp;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("err when call api supp confrim refund: "+e);
		}
		return false;
	}
	//Disable
	public Voucher GetPriceVoucher(String id, String userCreate) {
		String urlAPI = BaseUrl.Sup_GetPriceVoucherOld.replace("param1", String.valueOf(id)).replace("param2", String.valueOf(userCreate));
		System.out.println("log calling: "+id+" "+userCreate);
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<Voucher> resp = restTemplate.exchange(urlAPI, HttpMethod.GET, null,
					new ParameterizedTypeReference<Voucher>() {
					});
			if(resp.getStatusCode().is2xxSuccessful()) {
				return resp.getBody();
			}
		} catch (Exception e) {
			System.out.println("sup-err: "+e);
		}
		return null;
	}
	public boolean Sup_ConfirmReceivedMoneyOfAd(HandleStatusObject obj) {
		
		String urlAPI =BaseUrl.Sup_ConfirmReceivedMoneyOfAd;
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<Boolean> resp = restTemplate.postForEntity(urlAPI, obj, Boolean.class);
			//Boolean resp = restTemplate.getForObject(urlAPI, Boolean.class);
			if(resp.getStatusCode().is2xxSuccessful()) {
				return resp.getBody();
			}
			//return false;
		} catch (Exception e) {
			System.out.println("Supp Confirm Received Money Of Ad Err: "+e);
		}
		return false;
	}
	//7-1
	public BigDecimal Sup_getPriceRefundAdmin(int idSupp, int idInvoice) {
		String urlALI = BaseUrl.Sup_getPriceRefundAdmin.replace("param1", String.valueOf(idSupp)).replace("param2", String.valueOf(idInvoice));
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<BigDecimal> resp = restTemplate.getForEntity(urlALI, BigDecimal.class);
			if(resp.getStatusCode().is2xxSuccessful()) {
				return resp.getBody();
			}
		} catch (Exception e) {
			System.out.println("Data log-Sup_getPriceRefundAdmin: "+e.getMessage());
		}
		return BigDecimal.ZERO;
	}

}
