package com.project.calling;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.project.utils.BaseUrl;

public class BatchCalling {
	private static BatchCalling instance = null;
	private RestTemplate restTemplate;
	
	public static BatchCalling getInstance() {
		if (instance == null) {
			instance = new BatchCalling();
		}
		return instance;
	}
	
	/**
	 * @author MaiTran
	 */
	public boolean AD_autoCheckSupplierNotConfirmA() {
		boolean flagAuto = true;
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.AD_autoCheckSupplierNotConfirmA;							
			ResponseEntity<Boolean> result = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<Boolean>() {
					});	
			flagAuto = result.getBody();
		} catch (Exception e) {
			flagAuto = false;
			//
			System.out.println("ERROR AD_autoCheckSupplierNotConfirmA");
			System.out.println(e.getMessage());
		}
		return flagAuto;
	}
	public boolean AD_autoCheckConfirmA() {
		boolean flagAuto = true;
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.AD_autoCheckConfirmA;							
			ResponseEntity<Boolean> result = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<Boolean>() {
					});	
			flagAuto = result.getBody();
		} catch (Exception e) {
			flagAuto = false;
			//
			System.out.println("ERROR AD_autoCheckConfirmA");
			System.out.println(e.getMessage());
		}
		return flagAuto;
	}
	
	
	/**
	 * @author Kien
	 */
	public void Supp_AutoCheckConfirmS(int idSupplier) {
		try {
			restTemplate = new RestTemplate();
			String urlAPI = BaseUrl.Supp_AutoCheckConfirmS.replace("param1", String.valueOf(idSupplier));
			ResponseEntity<Boolean> resp = restTemplate.exchange(urlAPI, HttpMethod.GET, null,
					new ParameterizedTypeReference<Boolean>() {
					});
		} catch (Exception e) {
			System.out.println("Supp_Err - Supp_AutoCheckConfirmS : "+e.getMessage());
		}
	}
	public void Supp_AutoCheckSupplierNotConfirmS(int idSupplier) {
		try {
			restTemplate = new RestTemplate();
			String urlAPI = BaseUrl.Supp_AutoCheckSupplierNotConfirmS.replace("param1", String.valueOf(idSupplier));
			ResponseEntity<Boolean> resp = restTemplate.exchange(urlAPI, HttpMethod.GET, null,
					new ParameterizedTypeReference<Boolean>() {
					});
		} catch (Exception e) {
			System.out.println("Supp_Err - Supp_AutoCheckSupplierNotConfirmS: "+e.getMessage());
		}
	}
	
	/**
	 * @author Vinh
	 */
	public boolean autoConfirmSupplierInvoiceU(int idInvoice) {
		boolean flagAuto = true;
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.AUTO_CONF_INVOICED.replace("queryId", String.valueOf(idInvoice));							
			ResponseEntity<Boolean> result = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<Boolean>() {
					});	
			flagAuto = result.getBody();
		} catch (Exception e) {
			flagAuto = false;
			//
			System.out.println("ERROR autoConfirmSupplierInvoiceU");
			System.out.println(e.getMessage());
		}
		return flagAuto;
	}
	
	public boolean autoCheckNotConfirmSupplierU(int idInvoice) {
		boolean flagAuto = true;
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.AUTO_CHK_UCONF_S.replace("queryParam", String.valueOf(idInvoice));							
			ResponseEntity<Boolean> result = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<Boolean>() {
					});	
			flagAuto = result.getBody();
		} catch (Exception e) {
			flagAuto = false;
			//
			System.out.println("ERROR autoCheckNotConfirmSupplierU");
			System.out.println(e.getMessage());
		}
		return flagAuto;
	}
	
	public boolean autoStartVoucherType(String userType) {
		boolean flagAuto = true;
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.AUTO_START_V.replace("queryUser", String.valueOf(userType));							
			ResponseEntity<Boolean> result = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<Boolean>() {
					});	
			flagAuto = result.getBody();
		} catch (Exception e) {
			flagAuto = false;
			//
			System.out.println("ERROR autoStartVoucherType");
			System.out.println(e.getMessage());
		}
		return flagAuto;
	}
	
	public boolean autoEndVoucherType(String userType) {
		boolean flagAuto = true;
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.AUTO_END_V.replace("queryUser", String.valueOf(userType));							
			ResponseEntity<Boolean> result = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<Boolean>() {
					});	
			flagAuto = result.getBody();
		} catch (Exception e) {
			flagAuto = false;
			//
			System.out.println("ERROR autoEndVoucherType");
			System.out.println(e.getMessage());
		}
		return flagAuto;
	}
	
	public boolean autoStartDiscount(int idSupplier) {
		boolean flagAuto = true;
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.AUTO_START_D.replace("querySupp", String.valueOf(idSupplier));							
			ResponseEntity<Boolean> result = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<Boolean>() {
					});	
			flagAuto = result.getBody();
		} catch (Exception e) {
			flagAuto = false;
			//
			System.out.println("ERROR autoStartDiscount");
			System.out.println(e.getMessage());
		}
		return flagAuto;
	}
	
	public boolean autoEndDiscount(int idSupplier) {
		boolean flagAuto = true;
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.AUTO_END_D.replace("querySupp", String.valueOf(idSupplier));							
			ResponseEntity<Boolean> result = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<Boolean>() {
					});	
			flagAuto = result.getBody();
		} catch (Exception e) {
			flagAuto = false;
			//
			System.out.println("ERROR autoEndDiscount");
			System.out.println(e.getMessage());
		}
		return flagAuto;
	}
	
	public boolean autoStartEndDiscountUsers() {
		boolean flagAuto = true;
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<Boolean> result = restTemplate.exchange(BaseUrl.AUTO_STARTEND_DU, HttpMethod.GET, null,
					new ParameterizedTypeReference<Boolean>() {
					});	
			flagAuto = result.getBody();
		} catch (Exception e) {
			flagAuto = false;
			//
			System.out.println("ERROR autoEndDiscount");
			System.out.println(e.getMessage());
		}
		return flagAuto;
	}
	
	public boolean autoUpdateItemInCart(int idUsers) {
		boolean flagAuto = true;
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.AUTO_UPDATE_C.replace("queryId", String.valueOf(idUsers));							
			ResponseEntity<Boolean> result = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<Boolean>() {
					});	
			flagAuto = result.getBody();
		} catch (Exception e) {
			flagAuto = false;
			//
			System.out.println("ERROR autoStartDiscount");
			System.out.println(e.getMessage());
		}
		return flagAuto;
	}
	
}
