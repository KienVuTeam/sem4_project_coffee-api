package com.project.calling;

import java.net.URI;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.project.model.HandleStatusObject;
import com.project.modelview.InvoiceView;
import com.project.utils.BaseUrl;

public class InvoiceSupplierCalling {
	private static InvoiceSupplierCalling instance = null;
	private RestTemplate restTemplate;

	public static InvoiceSupplierCalling getInstance() {
		if (instance == null) {
			instance = new InvoiceSupplierCalling();
		}
		return instance;
	}
	
	/*
	 * Author Kien
	 */
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
				System.out.println(e.getMessage());
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
				System.out.println(e.getMessage());
			}
			return null;
		}

		public List<InvoiceView> GetTotalAmountOfProduct(int idSupplier) {
			String urlAPI = BaseUrl.Sup_GetTotalAmountOfProduct.replace("param1", String.valueOf(idSupplier));
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
				System.out.println(e.getMessage());
			}

			return null;
		}

		public List<InvoiceView> GetRefundtoCustomers(int idSupplier) {
			String urlAPI = BaseUrl.Sup_GetRefundtoCustomers.replace("param1", String.valueOf(idSupplier));
			try {
				restTemplate = new RestTemplate();
				ResponseEntity<List<InvoiceView>> resp = restTemplate.exchange(urlAPI, HttpMethod.GET, null,
						new ParameterizedTypeReference<List<InvoiceView>>() {
						});
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
			return null;
		}

		public List<InvoiceView> GetMoneyReceived(int idSupplier, int idInvoice) {
			String urlAPI = BaseUrl.Sup_GetMoneyReceived.replace("param1", String.valueOf(idInvoice)).replace("param2",
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
				System.out.println(e.getMessage());
			}

			return null;
		}

		// 14-10
		public List<InvoiceView> GetInvoiceDetail(int idSupplier, int id) {
			String urlAPI = BaseUrl.Sup_getInvoiceDetail.replace("param1", String.valueOf(idSupplier)).replace("param2",
					String.valueOf(id));
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
				System.out.println(e.getMessage());
			}
			return null;
		}

		// 19-10
		public boolean confirmInvoiceDetail(HandleStatusObject object) {
//			String urlAPI = BaseUrl.Sup_confirmInvoiceDetail.replace("param1", String.valueOf(object.getUserCase()))
//					.replace("param2", String.valueOf(object.getStatusType()))
//					.replace("param3", String.valueOf(object.getIdSupplier()))
//					.replace("param4", String.valueOf(object.getIdInvoice()))
//					.replace("param5", String.valueOf(object.getIdInvoiceDetails()));
			// String urlAPI =BaseUrl.Sup_confirmInvoiceDetail;
			try {
				restTemplate = new RestTemplate();
//				UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(urlAPI)
//			            .queryParam("userCase", object.getUserCase())
//			            .queryParam("statusType", object.getStatusType())
//			            .queryParam("idSupplier", object.getIdSupplier())
//			            .queryParam("idInvoice", object.getIdInvoice())
//			            .queryParam("idInvoiceDetails", object.getIdInvoiceDetails());

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
//				HttpHeaders headers = new HttpHeaders();								
//				headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));														
//				String aaa = "http://localhost:8081/api/Invoice/statusOfInvoice";
//				 URI uri = new URI(aaa);
//				HttpEntity<HandleStatusObject> req = new HttpEntity<HandleStatusObject>(object,headers);
//				ResponseEntity<Boolean> resp = restTemplate.exchange(uri, HttpMethod.GET, req,
//						new ParameterizedTypeReference<Boolean>() {
//						});
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

}
