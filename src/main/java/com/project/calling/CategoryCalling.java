package com.project.calling;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.project.model.Category;
import com.project.modelview.ProductView;
import com.project.utils.BaseUrl;

public class CategoryCalling {
	private static CategoryCalling instance = null;
	private RestTemplate restTemplate;

	public static CategoryCalling getInstance() {
		if (instance == null) {
			instance = new CategoryCalling();
		}
		return instance;
	}
	
	/**
	 * @author VINH ADD
	 */
	
	public List<Category> userGetAllAvalibleCategory(){
		List<Category> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();	
			ResponseEntity<List<Category>> responseEntity = restTemplate.exchange(BaseUrl.US_GETAVALIBLE_CATE,
					HttpMethod.GET, null, new ParameterizedTypeReference<List<Category>>() {
					});
			ls = responseEntity.getBody();	
		} catch (Exception e) {
			System.out.println("ERROR userGetAllAvalibleCategory");
			System.out.println(e.getMessage());
		}
		return ls;
	}
	
	public List<Category> userGetAvalibleCateSupp(int idSupplier){
		List<Category> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();	
			ResponseEntity<List<Category>> responseEntity = restTemplate.exchange(BaseUrl.US_GETAVALIBLE_CATESUPP.replace("queryParam", String.valueOf(idSupplier)),
					HttpMethod.GET, null, new ParameterizedTypeReference<List<Category>>() {
					});
			ls = responseEntity.getBody();	
		} catch (Exception e) {
			System.out.println("ERROR userGetAvalibleCateSupp");
			System.out.println(e.getMessage());
		}
		return ls;
	}

	/**
	 * @author MaiTran
	 */
	
	public List<Category> CallAllCate() {
		List<Category> lstCust = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<Category>> res = restTemplate.exchange(BaseUrl.AD_ALLCATE,HttpMethod.GET,null,
					new ParameterizedTypeReference<List<Category>>() {}
					);
			return res.getBody();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR CallAllCate");
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public Boolean CallInsertCate(Category addCate) {
		try {
			restTemplate = new RestTemplate();
			URI uri = new URI(BaseUrl.AD_INSERTCATE);
			ResponseEntity<Boolean> result = restTemplate.postForEntity(uri, addCate, Boolean.class);
			return true;
		} catch (Exception e) {
			System.out.println("ERROR CallInsertCate");
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public Boolean CallUpdateCate(Category modelUpdate) {
		Boolean flag = false;
		try {
			restTemplate = new RestTemplate();
			URI uri = new URI(BaseUrl.AD_UPDATECATE);
			ResponseEntity<Boolean> result = restTemplate.postForEntity(uri, modelUpdate, Boolean.class);
			return true;
		} catch (Exception e) {
			System.out.println("ERROR CallUpdateCate");
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public Boolean CallUpdateActiveCate(Category modelUpdate) {
		Boolean flag = false;
		try {
			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(BaseUrl.AD_UPDATEACTIVECATE);
			ResponseEntity<Boolean> result = restTemplate.postForEntity(uri, modelUpdate, Boolean.class);
			return true;
		} catch (Exception e) {
			System.out.println("ERROR CallUpdateActiveCate");
			System.out.println(e.getMessage());
			return false;
		}
	}
}
