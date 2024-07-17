package com.project.calling;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.project.modelview.InvoiceDetailsU;
import com.project.utils.BaseUrl;

public class InvoiceDetailsCalling {
	private static InvoiceDetailsCalling instance = null;
	private RestTemplate restTemplate;
	
	private InvoiceDetailsCalling() {}
	public static InvoiceDetailsCalling GetInstance() {
		if (instance == null) {
			instance = new InvoiceDetailsCalling();
		}
		return instance;
	}
	
	/**
	 * @author Vinh
	 */
	
	public List<InvoiceDetailsU> userGetInvoiceDetails(int idAccount,int idInvoice){
		List<InvoiceDetailsU> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_INVOICE_DETAILS.replace("paramInvoice", String.valueOf(idInvoice)).replace("paramAccount", String.valueOf(idAccount));
			ResponseEntity<List<InvoiceDetailsU>> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<InvoiceDetailsU>>() {
					});
			ls = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR userGetInvoiceDetails");
			System.out.println(e.getMessage());
		}
		return ls;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
