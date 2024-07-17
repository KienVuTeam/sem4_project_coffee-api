package com.project.calling;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.project.modelview.DiscountView;
import com.project.model.Discount;
import com.project.utils.BaseUrl;

import ch.qos.logback.core.model.ParamModel;

public class DiscountCalling {
	private static DiscountCalling instance = null;
	private RestTemplate restTemplate;
	
	private DiscountCalling() {}
	public static DiscountCalling GetInstance() {
		if (instance == null) {
			instance = new DiscountCalling();
		}
		return instance;
	}
	
	public List<DiscountView> CallGetAllDiscount(int idSupplier) {
		List<DiscountView> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<DiscountView>> responseEntity = restTemplate.exchange(
					BaseUrl.Sup_GetAllDiscount + "?idSupplier=" + idSupplier,
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<List<DiscountView>>() {
					});
			ls = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR CallGetAllDiscount");
		}
		return ls;
	}
	
	public List<DiscountView> CallFilterDiscountWeb(int idSupplier, int type) {
		List<DiscountView> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<DiscountView>> responseEntity = restTemplate.exchange(
					BaseUrl.Sup_GetFilterDiscountWeb + "?idSupplier=" + idSupplier + "&type=" + type,
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<List<DiscountView>>() {
					});
			ls = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR CallFilterDiscountWeb");
		}
		return ls;
	}
	
	public Boolean CallInsertDiscount(List<DiscountView> ls) {
		Boolean flag = false;
		try {
			restTemplate = new RestTemplate();
			restTemplate.postForEntity(BaseUrl.Sup_InsertDiscount, ls, Boolean.class);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR CallGetAllDiscount");
		}
		return flag;
	}
	
	public Boolean CallUpdateDiscount(DiscountView d) {
		Boolean flag = false;
		try {
			restTemplate = new RestTemplate();
			restTemplate.postForEntity(BaseUrl.Sup_UpdateDiscount, d, Boolean.class);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR CallUpdateDiscount");
		}
		return flag;
	}
	
	public Boolean CallDeleteDiscount(int indC) {
		boolean flag = false;
		DiscountView d = new DiscountView();
		d.setIndC(indC);
		try {
			restTemplate = new RestTemplate();
			restTemplate.postForObject(BaseUrl.Sup_DeleteDiscount, d, Boolean.class);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR CallDeleteDiscount");
		}
		return flag;
	}
	
	public Boolean RemoveDisOfPro(int idProduct, int indC) {
		boolean flag = false;
		DiscountView d = new DiscountView();
		d.setIndC(indC);
		d.setIdProduct(idProduct);
		try {
			restTemplate = new RestTemplate();
			restTemplate.postForObject(BaseUrl.Sup_RemoveDiscountOfPro, d, Boolean.class);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR RemoveDisOfPro");
		}
		return flag;
	}
	
	public List<DiscountView> CallDetailDiscount(int indC, int idSupplier) {
		List<DiscountView> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<DiscountView>> responseEntity = restTemplate.exchange(
					BaseUrl.Sup_DetailDiscount + "?indC=" + indC + "&idSupplier=" + idSupplier,
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<List<DiscountView>>() {
					});
			ls = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR CallDetailDiscount");
		}
		return ls;
	}
}
