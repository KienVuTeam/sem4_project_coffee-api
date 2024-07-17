package com.project.calling;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.project.model.ObjectMessage;
import com.project.model.Review;
import com.project.model.ReviewViewS;
import com.project.modelview.ReviewView;
import com.project.utils.BaseUrl;

public class ReviewCalling {
	private static ReviewCalling instance = null;
	private RestTemplate restTemplate;
	
	public static ReviewCalling getInstance() {
		if (instance == null) {
			instance = new ReviewCalling();
		}
		return instance;
	}
	
	/**
	 * @author Vinh
	 */
	
	public List<ReviewView> getUserReviewbyProductID(int idProduct){
		List<ReviewView> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_GETREVIEW_BYPRODUCT.replace("queryParam", String.valueOf(idProduct));
			//
			ResponseEntity<List<ReviewView>> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<ReviewView>>() {
					});
			ls = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR userReviewbyProductID");
			System.out.println(e.getMessage());
		}
		return ls;
	}
	
	public boolean userRatingProducts(Review objectInsert) {
		boolean flagInsert = true;
		try {
			restTemplate = new RestTemplate();
			URI uri = new URI(BaseUrl.US_RATINGS_PRODUCT);										
			ResponseEntity<Boolean> result = restTemplate.postForEntity(uri, objectInsert, boolean.class);		
			flagInsert = result.getBody();
		} catch (Exception e) {
			flagInsert = false;
			//
			System.out.println("ERROR userRatingProducts");
			System.out.println(e.getMessage());
		}
		return flagInsert;
	}
	
	/**
	 * @author Thuan
	 **/
	public List<ReviewViewS> CallGetDetailReview(int idProduct) {
		List<ReviewViewS> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<ReviewViewS>> responseEntity = restTemplate.exchange(
					BaseUrl.Sup_GetDetailReview + "?idProduct=" + idProduct,
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<List<ReviewViewS>>() {
					});
			ls = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR CallGetDetailReview");
			System.out.println(e.getMessage());
		}
		return ls;
	}
}
